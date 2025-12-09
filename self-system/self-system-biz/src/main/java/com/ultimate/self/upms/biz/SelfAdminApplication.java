package com.ultimate.self.upms.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
@MapperScan("com.ultimate.self.upms.biz.mapper")
public class SelfAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfAdminApplication.class, args);
    }

}