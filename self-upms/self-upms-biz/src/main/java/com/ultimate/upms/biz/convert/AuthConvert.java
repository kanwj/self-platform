package com.ultimate.upms.biz.convert;

import cn.hutool.core.collection.CollUtil;
import com.ultimate.self.common.core.util.object.BeanUtils;
import com.ultimate.upms.api.dataobject.AdminUserDO;
import com.ultimate.upms.api.dataobject.MenuDo;
import com.ultimate.upms.api.dataobject.RoleDo;
import com.ultimate.upms.api.enums.MenuTypeEnum;
import com.ultimate.upms.api.vo.AuthPermissionInfoRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.ultimate.self.common.core.util.collection.CollectionUtils.convertSet;
import static com.ultimate.self.common.core.util.collection.CollectionUtils.filterList;
import static com.ultimate.upms.api.dataobject.MenuDo.ID_ROOT;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/15 10:18
 */
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    default AuthPermissionInfoRespVO convert(AdminUserDO user, List<RoleDo> roleList, List<MenuDo> menuList){
        return AuthPermissionInfoRespVO.builder()
                .user(BeanUtils.toBean(user, AuthPermissionInfoRespVO.UserVO.class))
                .roles(convertSet(roleList, RoleDo::getCode))
                //权限标识信息
                .permissions(convertSet(menuList, MenuDo::getPermission))
                //菜单树
                .menus(buildMenuTree(menuList))
                .build();

    }

    AuthPermissionInfoRespVO.MenuVO convertTreeNode(MenuDo menu);

    /**
     * 将菜单列表构建成菜单树
     * @param menuDoList
     * @return
     */
    default List<AuthPermissionInfoRespVO.MenuVO> buildMenuTree(List<MenuDo> menuDoList){
        if(CollUtil.isEmpty(menuDoList)){
            return Collections.emptyList();
        }

        //移除按钮
        menuDoList.removeIf(menuDo -> MenuTypeEnum.BUTTON.getType().equals(menuDo.getType()));
        //排序，保证菜单的有序性
        menuDoList.sort(Comparator.comparing(MenuDo::getSort));
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
