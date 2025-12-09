package com.ultimate.self.upms.biz.service;

import com.ultimate.self.upms.api.dataobject.MenuDO;

import java.util.List;
import java.util.Set;

/**
 * 描述：菜单业务层
 * 作者：kanwj
 * 日期：2025/9/12 16:53
 */
public interface MenuService {

    List<MenuDO> getMenuList(Set<Long> ids);

    List<MenuDO> getMenuList();

    /**
     * 过滤掉关闭的菜单及其子菜单
     * @param list
     * @return
     */
    List<MenuDO> filterDisableMenus(List<MenuDO> list);
}
