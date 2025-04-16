package com.ultimate.self.auth.config;

import com.ultimate.self.auth.support.core.FormIdentityLoginConfigurer;
import com.ultimate.self.auth.support.core.SelfDaoAuthenticationProvider;
import com.ultimate.self.auth.support.handler.SelfAuthenticationFailureEventHandler;
import com.ultimate.self.auth.support.handler.SelfAuthenticationSuccessEventHandler;
//import com.ultimate.self.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import com.ultimate.self.common.core.config.CommonConstantsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class AuthorizationServerConfiguration {

    //private final OAuth2AuthorizationService authorizationService;

    @Autowired
    private CommonConstantsConfig commonConstantsConfig;

    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity httpSecurity, SelfAuthenticationSuccessEventHandler successEventHandler, SelfAuthenticationFailureEventHandler failureEventHandler) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();
        DefaultSecurityFilterChain securityFilterChain = httpSecurity.authorizeHttpRequests(authorizeRequests -> {
                    //自定义接口，端点暴露
                    authorizeRequests.antMatchers("/token/**", "/actuator/**", "/css/**", "/error").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                }).formLogin().and().build();

        //注入自定义授权模式实现
        addCustomOAuth2GrantAuthenticationProvider(httpSecurity);
        return securityFilterChain;
    }

    /**
     * 注入授权模式实现提供方
     *
     * 1. 密码模式 </br>
     * 2. 短信登录 </br>
     *
     */
    @SuppressWarnings("unchecked")
    private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);

//        OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new OAuth2ResourceOwnerPasswordAuthenticationProvider(
//                authenticationManager, authorizationService, oAuth2TokenGenerator());
//
//        OAuth2ResourceOwnerSmsAuthenticationProvider resourceOwnerSmsAuthenticationProvider = new OAuth2ResourceOwnerSmsAuthenticationProvider(
//                authenticationManager, authorizationService, oAuth2TokenGenerator());

        // 处理 UsernamePasswordAuthenticationToken
        http.authenticationProvider(new SelfDaoAuthenticationProvider(commonConstantsConfig));
//        // 处理 OAuth2ResourceOwnerPasswordAuthenticationToken
//        http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
//        // 处理 OAuth2ResourceOwnerSmsAuthenticationToken
//        http.authenticationProvider(resourceOwnerSmsAuthenticationProvider);
    }
}
