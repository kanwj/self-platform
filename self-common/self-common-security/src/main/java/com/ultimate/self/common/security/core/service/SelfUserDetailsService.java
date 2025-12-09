/*
 *    Copyright (c) 2018-2025, sfa All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: sfa
 */
package com.ultimate.self.common.security.core.service;

import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author sfa
 * @date 2018/8/15
 */
public interface SelfUserDetailsService extends UserDetailsService, Ordered {

	/**
	 * 是否支持此客户端校验
	 * @param clientId 请求客户端
	 * @param grantType 授权类型
	 * @return true/false
	 */
	default boolean support(String clientId, String grantType) {
		return true;
	}

	/**
	 * 排序值 默认取最大的
	 * @return 排序值
	 */
	default int getOrder() {
		return 0;
	}

	/**
	 * 通过用户实体查询
	 * @param loginUser user
	 * @return
	 */
	default UserDetails loadUserByUser(LoginUser loginUser) {
		return this.loadUserByUsername(loginUser.getUsername());
	}

}
