package com.ultimate.upms.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ultimate.upms.api.dataobject.MenuDo;
import com.ultimate.upms.api.dataobject.RoleMenuDo;
import com.ultimate.upms.api.dataobject.UserRoleDo;
import com.ultimate.upms.biz.mapper.RoleMenuMapper;
import com.ultimate.upms.biz.mapper.UserRoleMapper;
import com.ultimate.upms.biz.service.MenuService;
import com.ultimate.upms.biz.service.PermissionService;
import com.ultimate.upms.biz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.ultimate.self.common.core.util.collection.CollectionUtils.convertSet;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/12 16:55
 */
@Service

public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    public Set<Long> getUserRoleIdListByUserId(Long userId) {
        return convertSet(userRoleMapper.selectListByUserId(userId), UserRoleDo::getRoleId);
    }

    @Override
    public Set<Long> getRoleMenuListByRoleIds(Collection<Long> roleIds) {
        if(CollUtil.isEmpty(roleIds)){
            return Collections.emptySet();
        }
        // 如果是管理员的情况下，获得所有的菜单编号
        if(roleService.hasAnySuperAdmin(roleIds)){
            return convertSet(menuService.getMenuList(), MenuDo::getId);
        }
        // 如果是非管理员的情况下，获得拥有的菜单编号
        return convertSet(roleMenuMapper.selectListByRoleIds(roleIds), RoleMenuDo::getMenuId);
    }

}
