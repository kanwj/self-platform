package com.ultimate.self.auth.support.core;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class FormIdentityLoginConfigurer extends AbstractHttpConfigurer<FormIdentityLoginConfigurer, HttpSecurity> {

    private static final String REDIRECT_URL = "redirect_url";

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.formLogin(formLogin -> {
            formLogin.loginPage("/token/login");
            formLogin.loginProcessingUrl("/token/form");
            //formLogin.successHandler();
            //formLogin.failureHandler();
        }).logout().logoutSuccessHandler(((request, response, authentication) -> {
            if(response == null){
                return;
            }
            //获取请求参数中是否包含回调地址
            String redirectUrl = request.getParameter(REDIRECT_URL);
            if (StrUtil.isEmpty(redirectUrl))
                response.sendRedirect(redirectUrl);
            else if (StrUtil.isNotBlank(request.getHeader(HttpHeaders.REFERER)))
                //默认跳转referer地址
                response.sendRedirect(request.getHeader(HttpHeaders.REFERER));
        })).deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and().csrf().disable();
    }
}
