package com.ultimate.self.upms.biz.controller.admin.auth;

import cn.hutool.core.collection.CollUtil;
import com.ultimate.self.common.framework.enums.CommonStatusEnum;
import com.ultimate.self.common.framework.pojo.CommonResult;
import com.ultimate.self.upms.biz.convert.AuthConvert;
import com.ultimate.self.upms.biz.service.AdminUserService;
import com.ultimate.self.upms.biz.service.MenuService;
import com.ultimate.self.upms.biz.service.PermissionService;
import com.ultimate.self.upms.biz.service.RoleService;
import com.ultimate.self.upms.api.dataobject.AdminUserDO;
import com.ultimate.self.upms.api.dataobject.MenuDO;
import com.ultimate.self.upms.api.dataobject.RoleDO;
import com.ultimate.self.upms.api.vo.AuthPermissionInfoRespVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.ultimate.self.common.framework.util.collection.CollectionUtils.convertSet;
import static com.ultimate.self.common.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 描述：管理后台-认证
 * 作者：kanwj
 * 日期：2025/9/10 15:50
 */
@RestController
@Tag(name = "管理后台 - 认证")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/getPermissionInfo")
    @Operation(summary = "获取登录用户的权限信息")
    public CommonResult<AuthPermissionInfoRespVO> getPermissionInfo(){
        //1.1 获取用户信息
        AdminUserDO user = adminUserService.getUserById(getLoginUserId());
        if(user == null){
            return CommonResult.success(null);
        }

        //1.2 获取角色列表
        Set<Long> userRoleIds = permissionService.getUserRoleIdListByUserId(getLoginUserId());
        if(CollUtil.isEmpty(userRoleIds)){
            return CommonResult.success(AuthConvert.INSTANCE.convert(user, Collections.emptyList(), Collections.emptyList()));
        }
        List<RoleDO> roleList = roleService.getRoleList(userRoleIds);
        roleList.removeIf(roleDo -> CommonStatusEnum.DISABLE.getStatus().equals(roleDo.getStatus()));//移除禁用的角色

        //1.3 获取菜单列表
        Set<Long> roleMenuIds = permissionService.getRoleMenuListByRoleIds(convertSet(roleList, RoleDO::getId));
        List<MenuDO> menuList = menuService.getMenuList(roleMenuIds);
        menuList = menuService.filterDisableMenus(menuList);

        return CommonResult.success(AuthConvert.INSTANCE.convert(user, roleList, menuList));
    }

}
