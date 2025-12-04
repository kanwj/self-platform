package com.ultimate.self.common.security.core.service;

import cn.hutool.core.util.ArrayUtil;
import com.ultimate.self.common.framework.constant.CommonConstantsConfig;
import com.ultimate.self.common.framework.constant.SecurityConstants;
import com.ultimate.self.common.framework.pojo.CommonResult;
import com.ultimate.self.common.framework.util.ops.RetOps;
import com.ultimate.self.common.framework.util.web.WebUtils;
import com.ultimate.self.common.security.core.exception.LoginErrorException;
import com.ultimate.upms.api.dataobject.AdminUserDO;
import com.ultimate.upms.api.feign.RemoteUserService;
import com.ultimate.upms.api.vo.RoleSimpleRespVO;
import com.ultimate.upms.api.vo.UserProfileRespVO;
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
	private final RemoteUserService remoteUserService;

	@Autowired
	private CommonConstantsConfig commonConstantsConfig;

	// 登录流程使用
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		CommonResult<UserProfileRespVO> result = remoteUserService.getUserByUserName(username);

		return RetOps.of(result).getData().map(userInfo -> {
			Set<String> dbAuthsSet = new HashSet<>();
//			if (ArrayUtil.isNotEmpty(userInfo.getRoles())) {
//				// 获取角色
//				Arrays.stream(userInfo.getRoles().stream().map(RoleSimpleRespVO::getId)).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
//
//			}
			Collection<? extends GrantedAuthority> authorities = AuthorityUtils
					.createAuthorityList(dbAuthsSet.toArray(new String[0]));
			// 构造sfa用户
			return new LoginUser(userInfo.getId(),userInfo.getUsername(), userInfo.getDept().getId(), userInfo.getMobile(),
					"Avatar", userInfo.getNickname(), userInfo.getEmail(), userInfo.getTenantId()
					,userInfo.getPassword(), true, true,
					true,true, authorities,"");
			}).orElseThrow(() -> new LoginErrorException(result.getMsg()));
	}

	// check-token 使用
	@Override
	public UserDetails loadUserByUser(LoginUser loginUser) {
		// 根据 LoginUser 里面的信息 查询对应表 返回 UserDetails,  根据实际情况修改
		return this.loadUserByUsername(loginUser.getUsername());
	}


	@Override
	public boolean support(String clientId, String grantType) {
		LOGGER.info("-----------个人平台兼容鉴权模式,selfClientId={},requestClientId={}",commonConstantsConfig.getClientId(),clientId);
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
