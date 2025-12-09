package com.ultimate.self.upms.biz.controller.admin.user;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ultimate.self.common.framework.pojo.CommonResult;
import com.ultimate.self.upms.biz.service.DeptService;
import com.ultimate.self.upms.biz.service.PermissionService;
import com.ultimate.self.upms.biz.service.PostService;
import com.ultimate.self.upms.biz.service.RoleService;
import com.ultimate.self.upms.api.dataobject.AdminUserDO;
import com.ultimate.self.upms.api.dataobject.DeptDO;
import com.ultimate.self.upms.api.dataobject.PostDO;
import com.ultimate.self.upms.api.dataobject.RoleDO;
import com.ultimate.self.upms.api.vo.UserProfileRespVO;
import com.ultimate.self.upms.biz.convert.UserConvert;
import com.ultimate.self.upms.biz.mapper.AdminUserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.ultimate.self.common.framework.pojo.CommonResult.success;

@RestController
@Tag(name = "管理后台 - 用户")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Resource
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private PostService postService;

    @GetMapping("/getUserByUserName/{username}")
    @Operation(summary = "根据用户名获取用户相关信息")
    public CommonResult<UserProfileRespVO> getUserByUserName(@PathVariable("username") String username){
        // 获得用户基本信息
        AdminUserDO user = adminUserMapper.selectOne(new QueryWrapper<AdminUserDO>().eq("username", username));
        // 获得用户角色
        List<RoleDO> userRoles = roleService.getRoleListFromCache(permissionService.getUserRoleIdListByUserId(user.getId()));
        // 获得部门信息
        DeptDO dept = user.getDeptId() != null ? deptService.getDept(user.getDeptId()) : null;
        // 获得岗位信息
        List<PostDO> posts = CollUtil.isNotEmpty(user.getPostIds()) ? postService.getPostList(user.getPostIds()) : null;
        return success(UserConvert.INSTANCE.convert(user, userRoles, dept, posts));
    }
}
