package com.ultimate.self.upms.biz.convert;

import cn.hutool.core.collection.CollUtil;
import com.ultimate.self.common.framework.util.object.BeanUtils;
import com.ultimate.self.upms.api.dataobject.AdminUserDO;
import com.ultimate.self.upms.api.dataobject.MenuDO;
import com.ultimate.self.upms.api.dataobject.RoleDO;
import com.ultimate.self.upms.api.enums.MenuTypeEnum;
import com.ultimate.self.upms.api.vo.AuthPermissionInfoRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.ultimate.self.common.framework.util.collection.CollectionUtils.convertSet;
import static com.ultimate.self.common.framework.util.collection.CollectionUtils.filterList;
import static com.ultimate.self.upms.api.dataobject.MenuDO.ID_ROOT;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/15 10:18
 */
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    default AuthPermissionInfoRespVO convert(AdminUserDO user, List<RoleDO> roleList, List<MenuDO> menuList){
        return AuthPermissionInfoRespVO.builder()
                .user(BeanUtils.toBean(user, AuthPermissionInfoRespVO.UserVO.class))
                .roles(convertSet(roleList, RoleDO::getCode))
                //权限标识信息
                .permissions(convertSet(menuList, MenuDO::getPermission))
                //菜单树
                .menus(buildMenuTree(menuList))
                .build();

    }

    AuthPermissionInfoRespVO.MenuVO convertTreeNode(MenuDO menu);

    /**
     * 将菜单列表构建成菜单树
     * @param menuDoList
     * @return
     */
    default List<AuthPermissionInfoRespVO.MenuVO> buildMenuTree(List<MenuDO> menuDoList){
        if(CollUtil.isEmpty(menuDoList)){
            return Collections.emptyList();
        }

        //移除按钮
        menuDoList.removeIf(menuDo -> MenuTypeEnum.BUTTON.getType().equals(menuDo.getType()));
        //排序，保证菜单的有序性
        menuDoList.sort(Comparator.comparing(MenuDO::getSort));
        //构建菜单树
        Map<Long, AuthPermissionInfoRespVO.MenuVO> treeNodeMap = new LinkedHashMap<>();
        menuDoList.forEach(menuDo -> treeNodeMap.put(menuDo.getId(), AuthConvert.INSTANCE.convertTreeNode(menuDo)));
        //处理父子关系
        treeNodeMap.values().stream().filter(treeNode -> !ID_ROOT.equals(treeNode.getParentId())).forEach(childNode -> {
            AuthPermissionInfoRespVO.MenuVO parentNode = treeNodeMap.get(childNode.getParentId());
            if(parentNode == null){
                LoggerFactory.getLogger(getClass()).error("[buildRouterTree][resource({}) 找不到父资源({})]",
                        childNode.getId(), childNode.getParentId());
                return;
            }
            if(parentNode.getChildren() == null){
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return filterList(treeNodeMap.values(), node -> ID_ROOT.equals(node.getParentId()));
    }

}
