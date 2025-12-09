package com.ultimate.self.upms.biz.service;

import com.ultimate.self.upms.api.dataobject.DeptDO;

/**
 * 描述：部门 service 接口
 * 作者：kanwj
 * 日期：2025/12/3 15:17
 */
public interface DeptService {

    /**
     * 描述：根据id获取部门信息
     * 作者：kanwj
     * 日期：2025/12/3 15:19
     */
    DeptDO getDept(Long id);
}
