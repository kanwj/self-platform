package com.ultimate.upms.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ultimate.upms.api.dataobject.RoleDo;
import com.ultimate.upms.biz.mapper.RoleMapper;
import com.ultimate.upms.biz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<RoleDo> getRoleList(Collection<Long> ids) {
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
}
