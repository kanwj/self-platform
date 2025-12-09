package com.ultimate.self.upms.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ultimate.self.common.framework.enums.CommonStatusEnum;
import com.ultimate.self.upms.api.dataobject.MenuDO;
import com.ultimate.self.upms.biz.mapper.MenuMapper;
import com.ultimate.self.upms.biz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import static com.ultimate.self.common.framework.util.collection.CollectionUtils.convertMap;
import static com.ultimate.self.upms.api.dataobject.MenuDO.ID_ROOT;

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
    public List<MenuDO> getMenuList(Set<Long> ids) {
        if(CollUtil.isEmpty(ids)){
            return Collections.emptyList();
        }
        return menuMapper.selectBatchIds(ids);
    }

    @Override
    public List<MenuDO> getMenuList() {
        return menuMapper.selectList();
    }

    @Override
    public List<MenuDO> filterDisableMenus(List<MenuDO> list) {
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        List<MenuDO> enabledMenus = new ArrayList<>();
        Set<Long> disabledMenuIds = new HashSet<>();
        Map<Long, MenuDO> menuMap = convertMap(list, MenuDO::getId);
        for (MenuDO menuDo : list){
            if(!isMenuDisabled(menuDo, disabledMenuIds, menuMap)){
                enabledMenus.add(menuDo);
            }
        }
        return enabledMenus;
    }

    private Boolean isMenuDisabled(MenuDO node, Set<Long> disabledMenuIds, Map<Long, MenuDO> menuMap){
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
        MenuDO parentMenu = menuMap.get(parentId);
        if(parentMenu == null || isMenuDisabled(parentMenu, disabledMenuIds, menuMap)){
            disabledMenuIds.add(parentMenu.getId());
            return true;
        }
        return false;
    }

}
