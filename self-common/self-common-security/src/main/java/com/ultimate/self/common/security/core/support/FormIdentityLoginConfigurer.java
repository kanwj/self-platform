package com.ultimate.self.common.security.core.support;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

/**
 * 描述：基于授权码模式 统一认证登录 spring security & sas 都可以使用 所以抽取成 HttpConfigurer
 * 作者：kanwj
 * 日期：2025/12/5 9:06
 */
public final class FormIdentityLoginConfigurer
		extends AbstractHttpConfigurer<FormIdentityLoginConfigurer, HttpSecurity> {

	@Override
	public void init(HttpSecurity http) throws Exception {
		http.formLogin(formLogin -> {
			formLogin.loginProcessingUrl("/login");
			//formLogin.failureHandler(new FormAuthenticationFailureHandler());
			//formLogin.successHandler(new TenantSavedRequestAwareAuthenticationSuccessHandler());

		});
	}

}
