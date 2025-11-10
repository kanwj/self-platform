package com.ultimate.self.common.framework;

import com.ultimate.self.common.framework.util.SM3Utils;

import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String timestamp = System.currentTimeMillis() + "";
        String random = UUID.randomUUID().toString();
        StringBuffer buffer = new StringBuffer();
        buffer.append("token=").append("4f20d54d-7451-47c4-9efc-18eee41d1d25")
                .append("&timestamp=").append(timestamp)
                .append("&random=").append(random)
                .append("&secretkey=").append("f2e7171e45ba3f65924fdbe3a04958c0");
        String sign = SM3Utils.encrypt(buffer.toString()).toUpperCase();
        System.out.println(timestamp);
        System.out.println(random);
        System.out.println(sign);




    }
}