package com.ultimate.upms.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ultimate.upms.api.dataobject.MenuDo;
import com.ultimate.upms.biz.mapper.MenuMapper;
import com.ultimate.upms.biz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ultimate.self.common.core.enums.CommonStatusEnum;
import java.util.*;

import static com.ultimate.self.common.core.util.collection.CollectionUtils.convertMap;
import static com.ultimate.self.common.core.util.collection.CollectionUtils.convertSet;
import static com.ultimate.upms.api.dataobject.MenuDo.ID_ROOT;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/15 17:25
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuDo> getMenuList(Set<Long> ids) {
        if(CollUtil.isEmpty(ids)){
            return Collections.emptyList();
        }
        return menuMapper.selectBatchIds(ids);
    }

    @Override
    public List<MenuDo> getMenuList() {
        return menuMapper.selectList();
    }

    @Override
    public List<MenuDo> filterDisableMenus(List<MenuDo> list) {
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        List<MenuDo> enabledMenus = new ArrayList<>();
        Set<Long> disabledMenuIds = new HashSet<>();
        Map<Long, MenuDo> menuMap = convertMap(list, MenuDo::getId);
        for (MenuDo menuDo : list){
            if(!isMenuDisabled(menuDo, disabledMenuIds, menuMap)){
                enabledMenus.add(menuDo);
            }
        }
        return enabledMenus;
    }

    private Boolean isMenuDisabled(MenuDo node, Set<Long> disabledMenuIds, Map<Long, MenuDo> menuMap){
        // 如果已经判定是禁用的节点，直接结束
        if(disabledMenuIds.contains(node.getId())){
            return true;
        }
        //1.先判断自身是否禁用
        if(CommonStatusEnum.isDisable(node.getStatus())){
            disabledMenuIds.add(node.getId());
            return true;
        }
        //2.遍历到parent为根节点，则无需继续判断
        Long parentId = node.getParentId();
        if(ObjectUtil.equal(parentId, ID_ROOT)){
            return false;
        }
        //3.
        MenuDo parentMenu = menuMap.get(parentId);
        if(parentMenu == null || isMenuDisabled(parentMenu, disabledMenuIds, menuMap)){
            disabledMenuIds.add(parentMenu.getId());
            return true;
        }
        return false;
    }

}
