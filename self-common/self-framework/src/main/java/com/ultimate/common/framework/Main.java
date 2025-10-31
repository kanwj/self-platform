package com.ultimate.common.framework;

import com.ultimate.common.framework.util.SM3Utils;

import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String timestamp = System.currentTimeMillis() + "";
        String random = UUID.randomUUID().toString();
        StringBuffer buffer = new StringBuffer();
        buffer.append("token=").append("")
                .append("&timestamp=").append(timestamp)
                .append("&random=").append(random)
                .append("&secretkey=").append("");
        String sign = SM3Utils.encrypt(buffer.toString()).toUpperCase();
    }
}