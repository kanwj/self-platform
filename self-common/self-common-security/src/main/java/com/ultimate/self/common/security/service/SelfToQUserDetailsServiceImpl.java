package com.ultimate.self.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import com.ultimate.org.api.dto.UserInfo;
import com.ultimate.org.api.entity.User;
import com.ultimate.org.api.fegin.RemoteQiaUserService;
import com.ultimate.self.common.core.config.CommonConstantsConfig;
import com.ultimate.self.common.core.constant.SecurityConstants;
import com.ultimate.self.common.core.exception.LoginErrorException;
import com.ultimate.self.common.core.util.R;
import com.ultimate.self.common.core.util.RetOps;
import com.ultimate.self.common.core.util.WebUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 认证中心兼容qcds用户
 */
@RequiredArgsConstructor
public class SelfToQUserDetailsServiceImpl implements SelfUserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SelfToQUserDetailsServiceImpl.class);

	@Autowired
	private final RemoteQiaUserService remoteUserService;

	@Autowired
	private CommonConstantsConfig commonConstantsConfig;

	// 登录流程使用
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		String source = WebUtils.getRequest().getParameter("source");
		R<UserInfo> result = remoteUserService.getUserByUserName(username, source, SecurityConstants.FROM_IN);

		return RetOps.of(result).getData().map(userInfo -> {
			Set<String> dbAuthsSet = new HashSet<>();
			if (ArrayUtil.isNotEmpty(userInfo.getRoles())) {
				// 获取角色
				Arrays.stream(userInfo.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
				// 获取资源
				if(userInfo.getPermissions()!=null && userInfo.getPermissions().length>0){
					dbAuthsSet.addAll(Arrays.asList(userInfo.getPermissions()));
				}
			}
			Collection<? extends GrantedAuthority> authorities = AuthorityUtils
					.createAuthorityList(dbAuthsSet.toArray(new String[0]));
			User user = userInfo.getUser();
			// 构造sfa用户
			return new SfaUser(user.getId(),user.getUsername(), 1L, user.getMobile(),
					"Avatar", "nikename", userInfo.getUser().getName(), "Email"
					, 1L,user.getPassword(), true, true,
					"userType",true, true, authorities,user.getSalt(),
					user.getCode(), userInfo.getDealerCodeList(), userInfo.getOrgDealerList(),
					userInfo.getOrgCode(), userInfo.getOrgName(),userInfo.getOrgLevelCode(),userInfo.getDealerList());
			}).orElseThrow(() -> new LoginErrorException(result.getMsg()));
	}

	// check-token 使用
	@Override
	public UserDetails loadUserByUser(SfaUser sfaUser) {
		// 根据 SfaUser 里面的信息 查询对应表 返回 UserDetails,  根据实际情况修改
		return this.loadUserByUsername(sfaUser.getUsername());
	}


	@Override
	public boolean support(String clientId, String grantType) {
		LOGGER.info("sfa兼容鉴权模式,sfaClientId={},requestClientId={}",commonConstantsConfig.getClientId(),clientId);
		return commonConstantsConfig.getClientId().equals(clientId);
	}

	/**
	 * 排序值
	 * @return 排序值
	 */
	@Override
	public int getOrder() {
		return 10;
	}
}
