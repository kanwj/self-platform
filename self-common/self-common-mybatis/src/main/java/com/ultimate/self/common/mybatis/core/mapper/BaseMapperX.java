package com.ultimate.self.common.mybatis.core.mapper;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.base.MPJBaseMapper;

import java.util.Collection;
import java.util.List;

/**
 * 描述：在 Mybatis Plus的 BaseMapper 基础上拓展，提供更过的能力
 * 1. {@link BaseMapper} 为 Mybatis Plus 的基础接口，提供基础的CRUD能力
 * 2. {@link MPJBaseMapper} 为 Mybatis Plus Join 的基础接口，提供连表 Join 能力
 * 作者：kanwj
 * 日期：2025/9/12 9:34
 */
public interface BaseMapperX<T> extends MPJBaseMapper<T> {

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        if (CollUtil.isEmpty(values)) {
            return CollUtil.newArrayList();
        }
        return selectList(new LambdaQueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(){
        return selectList(new QueryWrapper<>());
    }
}
