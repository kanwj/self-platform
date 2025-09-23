package com.ultimate.self.common.core.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：通用状态枚举
 * 作者：kanwj
 * 日期：2025/9/16 14:45
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    ENABLE(0, "开启"),
    DISABLE(1, "关闭");

    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    public static boolean isEnable(Integer status) {
        return ObjectUtil.equal(ENABLE.status, status);
    }

    public static boolean isDisable(Integer status) {
        return ObjectUtil.equal(DISABLE.status, status);
    }
}
