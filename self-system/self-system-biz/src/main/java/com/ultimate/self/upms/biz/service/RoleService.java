package com.ultimate.self.upms.biz.service;

import com.ultimate.self.upms.api.dataobject.RoleDO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 描述：角色信息业务层
 * 作者：kanwj
 * 日期：2025/9/16 9:43
 */
@Service
public interface RoleService {

    /**
     * 获得角色列表
     * @param ids
     * @return
     */
    List<RoleDO> getRoleList(Collection<Long> ids);

    /**
     * 判断是否有管理员角色
     * @param roleIds
     * @return
     */
    Boolean hasAnySuperAdmin(Collection<Long> roleIds);

    /**
     * 描述：获得角色，从缓存中
     * 作者：kanwj
     * 日期：2025/12/3 16:13
     */
    RoleDO getRoleFromCache(Long id);

    /**
     * 描述：获得角色数组，从缓存中
     * 作者：kanwj
     * 日期：2025/12/3 9:42
     */
    List<RoleDO> getRoleListFromCache(Collection<Long> ids);

}
