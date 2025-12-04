package com.ultimate.upms.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ultimate.upms.api.dataobject.AdminUserDO;
import com.ultimate.upms.biz.mapper.AdminUserMapper;
import com.ultimate.upms.biz.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：后台用户 Service 实现类
 * 作者：kanwj
 * 日期：2025/9/12 9:52
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUserDO getUserById(Long id) {
        return adminUserMapper.selectById(id);
    }

    @Override
    public AdminUserDO getUserByUserName(String username) {
        AdminUserDO adminUser = adminUserMapper.selectOne(new QueryWrapper<AdminUserDO>().eq("username", username));
        if(adminUser == null){

        }

        return adminUserMapper.selectOne(new QueryWrapper<AdminUserDO>().eq("username", username));
    }
}
