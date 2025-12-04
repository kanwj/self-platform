package com.ultimate.upms.biz.mapper;

import com.ultimate.self.common.mybatis.core.mapper.BaseMapperX;
import com.ultimate.upms.api.dataobject.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/12 15:57
 */
@Mapper
public interface RoleMenuMapper extends BaseMapperX<RoleMenuDO> {

    default List<RoleMenuDO> selectListByRoleId(Long roleId){return selectList(RoleMenuDO::getRoleId, roleId);}

    default List<RoleMenuDO> selectListByRoleIds(Collection<Long> roleIds){return selectList(RoleMenuDO::getRoleId, roleIds);}

}
