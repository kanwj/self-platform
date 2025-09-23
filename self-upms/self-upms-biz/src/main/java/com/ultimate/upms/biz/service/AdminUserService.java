package com.ultimate.upms.biz.service;

import com.ultimate.upms.api.dataobject.AdminUserDO;

/**
 * 描述：用户业务层
 * 作者：kanwj
 * 日期：2025/9/12 9:51
 */
public interface AdminUserService {

    /**
     * 通过用户 ID 查询用户
     * @param id
     * @return
     */
    AdminUserDO getUserById(Long id);
}
