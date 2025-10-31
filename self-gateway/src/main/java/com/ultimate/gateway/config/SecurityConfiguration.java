//package com.ultimate.gateway.config;
//
//import com.ultimate.self.common.security.config.AuthorizeRequestsCustomizer;
//import com.ultimate.upms.api.enums.ApiConstants;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
//
///**
// * gateway 模块的 Security 配置
// */
//@Configuration(proxyBeanMethods = false, value = "gatewaySecurityConfiguration")
//public class SecurityConfiguration {
//
//    @Bean("gatewayAuthorizeRequestsCustomizer")
//    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
//        return new AuthorizeRequestsCustomizer() {
//
//            @Override
//            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
//                // TODO 芋艿：这个每个项目都需要重复配置，得捉摸有没通用的方案
//                // Swagger 接口文档
//                registry.requestMatchers("/v3/api-docs/**").permitAll()
//                        .requestMatchers("/webjars/**").permitAll()
//                        .requestMatchers("/swagger-ui").permitAll()
//                        .requestMatchers("/doc").permitAll()
//                        .requestMatchers("/doc/**").permitAll()
//                        .requestMatchers("/swagger-ui/**").permitAll();
//                // Druid 监控
//                registry.requestMatchers("/druid/**").permitAll();
//                // Spring Boot Actuator 的安全配置
//                registry.requestMatchers("/actuator").permitAll()
//                        .requestMatchers("/actuator/**").permitAll();
//                // RPC 服务的安全配置
//                registry.requestMatchers(ApiConstants.PREFIX + "/**").permitAll();
//            }
//
//        };
//    }
//
//}
