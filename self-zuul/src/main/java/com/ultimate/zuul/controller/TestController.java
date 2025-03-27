package com.ultimate.zuul.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 描述：test
 * 作者：kanwj
 * 日期：2025/3/3 17:37
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${test.zuul}")
    private String test;
    @RequestMapping("/zuul")
    private void test(){
        System.out.println(test);

    }

    public static void main(String[] args) {
        // 示例数据
        List<Double> list = Arrays.asList(1.1, 2.2, 3.3);
        Object obj = list;

        // 直接转换为 JSONArray
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(obj);
        System.out.println(jsonArray); // 输出: [1.1,2.2,3.3]

    }
}
