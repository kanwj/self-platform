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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.io.Serializable;
import java.util.*;

/**
 * @author sfa
 * @date 2020/4/16 扩展用户信息
 */
public class LoginUser extends User implements OAuth2AuthenticatedPrincipal, Serializable {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	/**
	 * 扩展属性，方便存放oauth 上下文相关信息
	 */
	private final Map<String, Object> attributes = new HashMap<>();

	/**
	 * 用户ID
	 */
	@Getter
	@Setter
	private Long id;

	/**
	 * 部门ID
	 */
	@Getter
	@Setter
	private Long deptId;

	/**
	 * 手机号
	 */
	@Getter
	@Setter
	private String mobile;

	/**
	 * 头像
	 */
	@Getter
	@Setter
	private String avatar;

	/**
	 * 租户ID
	 */
	@Getter
	@Setter
	private Long tenantId;

	/**
	 * 拓展字段:昵称
	 */
	@Getter
	@Setter
	private String nickname;

	/**
	 * 用户账号
	 */
	@Getter
	@Setter
	private String username;

	/**
	 * 拓展字段:邮箱
	 */
	@Getter
	@Setter
	private String email;

	/**
	 * 盐值
	 */
	@Getter
	@Setter
	private String salt;

	@JsonCreator
	public LoginUser(@JsonProperty("id") Long id, @JsonProperty("username") String username,
					 @JsonProperty("deptId") Long deptId, @JsonProperty("phone") String mobile,
					 @JsonProperty("avatar") String avatar, @JsonProperty("nickname") String nickname,
					 @JsonProperty("email") String email,
					 @JsonProperty("tenantId") Long tenantId, @JsonProperty("password") String password,
					 @JsonProperty("enabled") boolean enabled, @JsonProperty("accountNonExpired") boolean accountNonExpired,
					 @JsonProperty("credentialsNonExpired") boolean credentialsNonExpired,
					 @JsonProperty("accountNonLocked") boolean accountNonLocked,
					 @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities, @JsonProperty("salt") String salt) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.deptId = deptId;
		this.mobile = mobile;
		this.avatar = avatar;
		this.tenantId = tenantId;
		this.nickname = nickname;
		this.username = username;
		this.email = email;
		this.salt = salt;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public String getName() {
		return null;
	}
}
