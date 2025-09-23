package com.ultimate.upms.biz.service;

import com.ultimate.upms.api.dataobject.RoleDo;
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
    List<RoleDo> getRoleList(Collection<Long> ids);

    /**
     * 判断是否有管理员角色
     * @param roleIds
     * @return
     */
    Boolean hasAnySuperAdmin(Collection<Long> roleIds);

}
