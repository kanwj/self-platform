package com.ultimate.self.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {"com.ultimate.self.auth"})
public class SelfAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfAuthApplication.class, args);
    }

}
