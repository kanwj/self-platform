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

package com.ultimate.self.common.security.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ultimate.org.api.dto.OrgDealerDto;
import com.ultimate.org.api.entity.Dealer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
public class SfaUser extends User implements OAuth2AuthenticatedPrincipal, Serializable {

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
	private String phone;

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
	 * 拓展字段:姓名
	 */
	@Getter
	@Setter
	private String name;

	/**
	 * 拓展字段:邮箱
	 */
	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String userType;

	/**
	 * 盐值
	 */
	@Getter
	@Setter
	private String salt;

	/**
	 * 员工编号
	 */
	@Getter
	@Setter
	private String code;

	/**
	 * 用户所属经销商编码集合
	 */
	@Getter
	@Setter
	private Set<String> dealerCodeList;

	/**
	 * 用户所属组织集合
	 */
	@Getter
	@Setter
	private List<OrgDealerDto> orgDealerList;

	/**
	 * 用户默认组织编码
	 */
	@Getter
	@Setter
	private String orgCode;

	/**
	 * 用户默认组织名
	 */
	@Getter
	@Setter
	private String orgName;

	/**
	 * 用户默认组织级别编码
	 */
	@Getter
	@Setter
	private String orgLevelCode;

	/**
	 * 用户经销商集合
	 */
	@Getter
	@Setter
	private List<Dealer> dealerList;

	/**
	 * Construct the <code>User</code> with the details required by
	 * {@link DaoAuthenticationProvider}.
	 * @param id 用户ID
	 * @param deptId 部门ID
	 * @param tenantId 租户ID
	 * @param nickname 昵称
	 * @param name 姓名
	 * @param email 邮箱 the username presented to the
	 * <code>DaoAuthenticationProvider</code>
	 * @param password the password that should be presented to the
	 * <code>DaoAuthenticationProvider</code>
	 * @param enabled set to <code>true</code> if the user is enabled
	 * @param accountNonExpired set to <code>true</code> if the account has not expired
	 * @param credentialsNonExpired set to <code>true</code> if the credentials have not
	 * expired
	 * @param accountNonLocked set to <code>true</code> if the account is not locked
	 * @param authorities the authorities that should be granted to the caller if they
	 * presented the correct username and password and the user is enabled. Not null.
	 * @throws IllegalArgumentException if a <code>null</code> value was passed either as
	 * a parameter or as an element in the <code>GrantedAuthority</code> collection
	 */
	@JsonCreator
	public SfaUser(@JsonProperty("id") Long id, @JsonProperty("username") String username,
                   @JsonProperty("deptId") Long deptId, @JsonProperty("phone") String phone,
                   @JsonProperty("avatar") String avatar, @JsonProperty("nickname") String nickname,
                   @JsonProperty("name") String name, @JsonProperty("email") String email,
                   @JsonProperty("tenantId") Long tenantId, @JsonProperty("password") String password,
                   @JsonProperty("enabled") boolean enabled, @JsonProperty("accountNonExpired") boolean accountNonExpired,
                   @JsonProperty("userType") String userType,
                   @JsonProperty("credentialsNonExpired") boolean credentialsNonExpired,
                   @JsonProperty("accountNonLocked") boolean accountNonLocked,
                   @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities, @JsonProperty("salt") String salt,
                   @JsonProperty("code") String code,
                   @JsonProperty("dealerCodeList") Set<String> dealerCodeList,
                   @JsonProperty("orgDealerList") List<OrgDealerDto> orgDealerList,
                   @JsonProperty("orgCode") String orgCode, @JsonProperty("orgName") String orgName,
                   @JsonProperty("orgLevelCode") String orgLevelCode,
                   @JsonProperty("dealerList") List<Dealer> dealerList) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.deptId = deptId;
		this.phone = phone;
		this.avatar = avatar;
		this.tenantId = tenantId;
		this.nickname = nickname;
		this.name = name;
		this.email = email;
		this.userType = userType;
		this.salt = salt;
		this.code = code;
		this.dealerCodeList = dealerCodeList;
		this.orgDealerList = orgDealerList;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgLevelCode = orgLevelCode;
		this.dealerList = dealerList;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

}
