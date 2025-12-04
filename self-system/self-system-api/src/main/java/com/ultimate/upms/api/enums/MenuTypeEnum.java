package com.ultimate.upms.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：菜单类型枚举
 * 作者：kanwj
 * 日期：2025/9/16 14:45
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    DIR(1), // 目录
    MENU(2), // 菜单
    BUTTON(3); // 按钮

    /**
     * 类型
     */
    private final Integer type;
}
