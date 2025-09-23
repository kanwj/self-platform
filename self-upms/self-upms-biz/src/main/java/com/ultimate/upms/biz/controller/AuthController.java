package com.ultimate.upms.biz.controller;

import cn.hutool.core.collection.CollUtil;
import com.ultimate.self.common.core.enums.CommonStatusEnum;
import com.ultimate.self.common.core.util.R;
import com.ultimate.upms.api.dataobject.AdminUserDO;
import com.ultimate.upms.api.dataobject.MenuDo;
import com.ultimate.upms.api.dataobject.RoleDo;
import com.ultimate.upms.biz.convert.AuthConvert;
import com.ultimate.upms.biz.service.AdminUserService;
import com.ultimate.upms.biz.service.MenuService;
import com.ultimate.upms.biz.service.PermissionService;
import com.ultimate.upms.biz.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.ultimate.self.common.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.ultimate.self.common.core.util.collection.CollectionUtils.convertSet;

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

    @RequestMapping("/getPermissionInfo")
    public R getPermissionInfo(){
        //1.1 获取用户信息
        AdminUserDO user = adminUserService.getUserById(getLoginUserId());
        if(user == null){
            return R.ok(null);
        }

        //1.2 获取角色列表
        Set<Long> userRoleIds = permissionService.getUserRoleIdListByUserId(getLoginUserId());
        if(CollUtil.isEmpty(userRoleIds)){
            return R.ok(AuthConvert.INSTANCE.convert(user, Collections.emptyList(), Collections.emptyList()));
        }
        List<RoleDo> roleList = roleService.getRoleList(userRoleIds);
        roleList.removeIf(roleDo -> CommonStatusEnum.DISABLE.getStatus().equals(roleDo.getStatus()));//移除禁用的角色

        //1.3 获取菜单列表
        Set<Long> roleMenuIds = permissionService.getRoleMenuListByRoleIds(convertSet(roleList, RoleDo::getId));
        List<MenuDo> menuList = menuService.getMenuList(roleMenuIds);
        menuList = menuService.filterDisableMenus(menuList);

        return R.ok(AuthConvert.INSTANCE.convert(user, roleList, menuList));
    }
}
