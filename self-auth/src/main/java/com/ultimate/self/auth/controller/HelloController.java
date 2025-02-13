package com.ultimate.self.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：$
 * 作者：kanwj
 * 日期：2024/12/24$ 9:32$
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/sayHello")
    public String hello() {

        return "Hello, 来跟  学习 Spring Security吧!";
    }
}
