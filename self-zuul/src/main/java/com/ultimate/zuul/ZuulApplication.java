package com.ultimate.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述：zuul网关
 * 作者：kanwj
 * 日期：2025/2/12 10:10
 */
@SpringBootApplication
//@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class);
    }
}
