package com.ultimate.upms.biz.service.impl;

import com.ultimate.upms.api.dataobject.DeptDO;
import com.ultimate.upms.biz.mapper.DeptMapper;
import com.ultimate.upms.biz.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 描述：部门 Service 实现类
 * 作者：kanwj
 * 日期：2025/12/3 15:19
 */
@Service
@Validated
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public DeptDO getDept(Long id) {
        return deptMapper.selectById(id);
    }
}
