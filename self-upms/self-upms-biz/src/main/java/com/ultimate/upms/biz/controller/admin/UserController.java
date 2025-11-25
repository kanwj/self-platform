package com.ultimate.upms.biz.controller.admin;

import com.ultimate.self.common.core.util.R;
import com.ultimate.upms.api.dataobject.AdminUserDO;
import com.ultimate.upms.biz.service.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：用户管理
 * 作者：kanwj
 * 日期：2025/11/4 10:27
 */
@RestController
@Tag(name = "管理后台 - 用户")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/getUserByUserName/{username}")
    public R<AdminUserDO> getUserByUserName(@PathVariable("username") String username){
        return R.ok(adminUserService.getUserByUserName(username));
    }
}
