package com.ultimate.gateway.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：配置类
 * 作者：kanwj
 * 日期：2025/4/2 11:02
 */
@Configuration
public class GlobalFilterConfig {
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {


        return (registry) -> registry.config().commonTags("application", applicationName);
    }
}
