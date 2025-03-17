package com.ultimate.upms.biz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication(scanBasePackages = {"com.ultimate.upms"})
public class SelfAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfAdminApplication.class, args);
    }

}