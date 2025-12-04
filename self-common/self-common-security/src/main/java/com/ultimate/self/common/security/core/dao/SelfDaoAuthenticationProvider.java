package com.ultimate.self.common.security.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ultimate.self.common.framework.constant.CommonConstantsConfig;
import com.ultimate.self.common.framework.constant.SecurityConstants;
import com.ultimate.self.common.framework.util.AESUtils;
import com.ultimate.self.common.framework.util.web.WebUtils;
import com.ultimate.self.common.security.core.password.SelfPasswordEncoder;
import com.ultimate.self.common.security.core.exception.LoginErrorException;
import com.ultimate.self.common.security.core.service.SelfUserDetailsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author sfa
 * @date 2022-06-04
 */
@Slf4j
public class SelfDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	/**
	 * The plaintext password used to perform PasswordEncoder#matches(CharSequence,
	 * String)} on when the user is not found to avoid SEC-2056.
	 */
	private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

	private final static BasicAuthenticationConverter basicConvert = new BasicAuthenticationConverter();

	private PasswordEncoder passwordEncoder;
	private CommonConstantsConfig commonConstantsConfig;

	/**
	 * The password used to perform {@link PasswordEncoder#matches(CharSequence, String)}
	 * on when the user is not found to avoid SEC-2056. This is necessary, because some
	 * {@link PasswordEncoder} implementations will short circuit if the password is not
	 * in a valid format.
	 */
	private volatile String userNotFoundEncodedPassword;

	private UserDetailsService userDetailsService;

	private UserDetailsPasswordService userDetailsPasswordService;

	public SelfDaoAuthenticationProvider(CommonConstantsConfig commonConstantsConfig) {
		this.commonConstantsConfig = commonConstantsConfig;
		setMessageSource(SpringUtil.getBean("securityMessageSource"));
		setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
	}

	@Override
	@SneakyThrows
	@SuppressWarnings("deprecation")
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		// app 模式不用校验密码
		String grantType = WebUtils.getRequest().getParameter(OAuth2ParameterNames.GRANT_TYPE);
		String clientId = WebUtils.getRequest().getParameter(OAuth2ParameterNames.CLIENT_ID);
		String source = WebUtils.getRequest().getParameter("source");

		String mode = WebUtils.getRequest().getParameter("mode");
		log.info("---------mode:{}", mode);
		if (StrUtil.isBlank(clientId)) {
			clientId = basicConvert.convert(WebUtils.getRequest()).getName();
		}

		if (StrUtil.equals(SecurityConstants.APP, grantType)) {
			return;
		}

		if (authentication.getCredentials() == null) {
			this.logger.debug("Failed to authenticate since no credentials provided");
			throw new BadCredentialsException(this.messages
					.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}

		String presentedPassword = null;
		if (StrUtil.isNotBlank(source)) {
			try {
				presentedPassword = AESUtils.decrypt(authentication.getCredentials().toString());
			} catch (Exception e) {
				this.logger.debug("密码非加密");
			}
		} else {
			presentedPassword = authentication.getCredentials().toString();
		}

		String nacosClientId = commonConstantsConfig.getClientId();
		//支持自定义密码解密器
		if (StrUtil.isNotBlank(mode) && mode.equals("sms")) {
			String phone = WebUtils.getRequest().getParameter("username");
			RedisTemplate bean = SpringUtil.getBean(RedisTemplate.class);
			Object code = bean.opsForValue().get(String.format(SecurityConstants.loginCacheKey, phone));
			log.info("从redis中获取的验证码为:{}", code);
			if (Objects.nonNull(code) && !Objects.equals(code, presentedPassword)) {
				throw new LoginErrorException("验证码输入错误/过期!");
			} else if (Objects.isNull(code)){
				throw new LoginErrorException("请先获取手机验证码!");
			}
		} else if(nacosClientId.equals(clientId)){
			setPasswordEncoder(new SelfPasswordEncoder());
			if (!this.passwordEncoder.matches(presentedPassword, userDetails.getUsername())) {
				setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
				this.logger.debug("Failed to authenticate since password does not match stored value");
				throw new BadCredentialsException(this.messages
						.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
			}
		} else{
			if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
				this.logger.debug("Failed to authenticate since password does not match stored value");
				throw new BadCredentialsException(this.messages
						.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
			}
		}
		setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
	}

	@SneakyThrows
	@Override
	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
		prepareTimingAttackProtection();
		String grantType = WebUtils.getRequest().getParameter(OAuth2ParameterNames.GRANT_TYPE);
		String clientId = WebUtils.getRequest().getParameter(OAuth2ParameterNames.CLIENT_ID);

		if (StrUtil.isBlank(clientId)) {
			clientId = basicConvert.convert(WebUtils.getRequest()).getName();
		}

		Map<String, SelfUserDetailsService> userDetailsServiceMap = SpringUtil
				.getBeansOfType(SelfUserDetailsService.class);

		String finalClientId = clientId;
		Optional<SelfUserDetailsService> optional = userDetailsServiceMap.values().stream()
				.filter(service -> service.support(finalClientId, grantType))
				.max(Comparator.comparingInt(Ordered::getOrder));

		if (!optional.isPresent()) {
			throw new InternalAuthenticationServiceException("UserDetailsService error , not register");
		}

		try {
			UserDetails loadedUser = optional.get().loadUserByUsername(username);
			if (loadedUser == null) {
				throw new InternalAuthenticationServiceException(
						"UserDetailsService returned null, which is an interface contract violation");
			}
			return loadedUser;
		}
		catch (LoginErrorException ex) {
			mitigateAgainstTimingAttack(authentication);
			throw ex;
		}
		catch (InternalAuthenticationServiceException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
			UserDetails user) {
		boolean upgradeEncoding = this.userDetailsPasswordService != null
				&& this.passwordEncoder.upgradeEncoding(user.getPassword());
		if (upgradeEncoding) {
			String presentedPassword = authentication.getCredentials().toString();
			String newPassword = this.passwordEncoder.encode(presentedPassword);
			user = this.userDetailsPasswordService.updatePassword(user, newPassword);
		}
		return super.createSuccessAuthentication(principal, authentication, user);
	}

	private void prepareTimingAttackProtection() {
		if (this.userNotFoundEncodedPassword == null) {
			this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
		}
	}

	private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
		if (authentication.getCredentials() != null) {
			String presentedPassword = authentication.getCredentials().toString();
			this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
		}
	}

	/**
	 * Sets the PasswordEncoder instance to be used to encode and validate passwords. If
	 * not set, the password will be compared using
	 * {@link PasswordEncoderFactories#createDelegatingPasswordEncoder()}
	 * @param passwordEncoder must be an instance of one of the {@code PasswordEncoder}
	 * types.
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
		this.passwordEncoder = passwordEncoder;
		this.userNotFoundEncodedPassword = null;
	}

	protected PasswordEncoder getPasswordEncoder() {
		return this.passwordEncoder;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return this.userDetailsService;
	}

	public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsPasswordService) {
		this.userDetailsPasswordService = userDetailsPasswordService;
	}

}
