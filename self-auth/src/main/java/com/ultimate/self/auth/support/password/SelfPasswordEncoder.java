package com.ultimate.self.auth.support.password;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import com.ultimate.self.common.core.util.WebUtils;
import com.ultimate.self.common.security.service.SelfUserDetailsService;
import com.ultimate.self.common.security.service.SfaUser;
import com.ultimate.self.common.security.util.MD5Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * 自定义密码加密器
 */
@RequiredArgsConstructor
@Slf4j
public class SelfPasswordEncoder implements PasswordEncoder {
    public static final String BIG_BRACKETS_LEFT = "{";
    public static final String BIG_BRACKETS_RIGHT = "}";
    private final static BasicAuthenticationConverter basicConvert = new BasicAuthenticationConverter();
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }

    /**
     * 密码加密过程
     *
     * @param rawPassword 原文
     * @return 密码
     */
    @Override
    public String encode(CharSequence rawPassword) {
        String salt = UUID.randomUUID().toString().replaceAll("\\-", "");
        String text = rawPassword + BIG_BRACKETS_LEFT + salt + BIG_BRACKETS_RIGHT;
        // 使用MD5 加密
        return MD5Util.encode(text);
    }

    /**
     * spring security 密码匹配过程
     *
     * @param rawPassword     用户输入的原文
     * @param username 用户名
     * @return true/false
     */
    @Override
    public boolean matches(CharSequence rawPassword, String username) {
        log.info("-----------开始进行密码校验：rawPassword：{}，username：{}",rawPassword,username);
        // 1.从数据库获取用户相关信息
        String grantType = WebUtils.getRequest().getParameter(OAuth2ParameterNames.GRANT_TYPE);
        String clientId = WebUtils.getRequest().getParameter(OAuth2ParameterNames.CLIENT_ID);
        log.info("-----------grantType：{}，clientId：{}",grantType,clientId);
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
        UserDetails userDetails = null;
        try {
            userDetails = optional.get().loadUserByUsername(username);
            log.info("------------用户信息:userDetails：{}", JSONObject.toJSON(userDetails));
            if (userDetails == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
        }catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
        SfaUser sfaUser = (SfaUser) userDetails;

        String salt = sfaUser.getSalt();
        String encodedPassword = sfaUser.getPassword();
        log.info("----------salt:{},encodedPassword:{}",salt,encodedPassword);
        // 2. 使用输入的明文+随机盐 继续加密
        String text = rawPassword + BIG_BRACKETS_LEFT + salt + BIG_BRACKETS_RIGHT;
        // 3. 比较加密结果和数据库存储的密文是否一致
        return MD5Util.encode(text).equals(encodedPassword);
    }
}
