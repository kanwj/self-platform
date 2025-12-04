package com.ultimate.upms.api.enums;

/**
 * 描述：账号状态枚举类
 * 作者：kanwj
 * 日期：2025/12/2 16:13
 */
public enum UserDetailsEnum {

    USER_IS_NOT_EXISTS("USER_IS_NOT_EXISTS", "账号不存在"),
    USER_IS_NOT_OPEN("USER_IS_NOT_OPEN", "账号未开通"),
    USER_IS_NOT_ENABLED("USER_IS_NOT_ENABLED", "账号未启用"),
    USER_IS_NOT_ASSIGN_ORG("USER_IS_NOT_ASSIGN_ORG","用户未分配组织"),
    USER_IS_DEALER_APP("USER_IS_DEALER_APP","当前角色不允许从APP登陆"),
    USER_IS_SMALL("USER_IS_SMALL","当前角色不允许从小程序登陆");

    private final String code;
    private final String name;

    UserDetailsEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
