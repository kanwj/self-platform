package com.ultimate.self.common.core.util.object;

import cn.hutool.core.bean.BeanUtil;

/**
 * 描述：Bean工具类
 * 1.默认使用 {@link cn.hutool.core.bean.BeanUtil} 作为实现类，虽然不同bean工具的性能有差别，但是对于绝大多数项目，不用在意这点性能
 * 2.针对复杂的对象转换，通过 mapstruct+default 配合实现
 * 作者：kanwj
 * 日期：2025/9/15 14:45
 */
public class BeanUtils {

    public static <T> T toBean(Object source, Class<T> targetClass){
        return BeanUtil.toBean(source, targetClass);
    }
}
