package com.ultimate.org.biz.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：111
 * 作者：kanwj
 * 日期：2025/3/27 15:12
 */
@RestController
public class BlogController {
    //表示db这个数据库
    @Value("${org.dataSource}")
    private String dbsource;

    @GetMapping("/blog/info/{id}")
    public String getInfo(@PathVariable("id") Integer id){
        return dbsource+id;
    }
}
