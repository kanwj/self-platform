package com.ultimate.upms.biz.mapper;

import com.ultimate.self.common.mybatis.core.mapper.BaseMapperX;
import com.ultimate.upms.api.dataobject.RoleMenuDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/12 15:57
 */
@Mapper
public interface RoleMenuMapper extends BaseMapperX<RoleMenuDo> {

    default List<RoleMenuDo> selectListByRoleId(Long roleId){return selectList(RoleMenuDo::getRoleId, roleId);}

    default List<RoleMenuDo> selectListByRoleIds(Collection<Long> roleIds){return selectList(RoleMenuDo::getRoleId, roleIds);}

}
