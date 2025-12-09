package com.ultimate.self.upms.biz.service;

import java.util.Collection;
import java.util.Set;

/**
 * 描述：权限处理业务层
 * 提供用户-角色、角色-菜单、角色-部门的关联权限处理
 * 作者：kanwj
 * 日期：2025/9/12 16:53
 */
public interface PermissionService {
    Set<Long> getUserRoleIdListByUserId(Long userId);

    Set<Long> getRoleMenuListByRoleIds(Collection<Long> roleIds);
}
