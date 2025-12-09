package com.ultimate.self.upms.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ultimate.self.common.framework.util.collection.CollectionUtils;
import com.ultimate.self.upms.api.dataobject.RoleDO;
import com.ultimate.self.upms.biz.framework.redis.RedisKeyConstants;
import com.ultimate.self.upms.biz.mapper.RoleMapper;
import com.ultimate.self.upms.biz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/16 9:50
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDO> getRoleList(Collection<Long> ids) {
        if(CollUtil.isEmpty(ids)){
            return Collections.emptyList();
        }
        return roleMapper.selectBatchIds(ids);
    }

    @Override
    public Boolean hasAnySuperAdmin(Collection<Long> roleIds) {
        if(CollUtil.isEmpty(roleIds)){
            return Boolean.FALSE;
        }
        return roleMapper.selectBatchIds(roleIds).stream().anyMatch(roleDo -> "super_admin".equals(roleDo.getCode()));
    }

    @Override
    @Cacheable(value = RedisKeyConstants.ROLE, key = "#id",
            unless = "#result == null")
    public RoleDO getRoleFromCache(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<RoleDO> getRoleListFromCache(Collection<Long> ids) {
        if(CollUtil.isEmpty(ids)){
            return new ArrayList<>();
        }
        // 这里采用 for 循环从缓存中获取，主要考虑 Spring CacheManager 无法批量操作的问题
        RoleServiceImpl self = getSelf();
        return CollectionUtils.convertList(ids, self::getRoleFromCache);
    }

    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private RoleServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }
}
