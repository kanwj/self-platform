package com.ultimate.self.upms.biz.mapper;

import com.ultimate.self.common.mybatis.core.mapper.BaseMapperX;
import com.ultimate.self.upms.api.dataobject.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/12 15:57
 */
@Mapper
public interface UserRoleMapper extends BaseMapperX<UserRoleDO> {

    default List<UserRoleDO> selectListByUserId(Long userId){return selectList(UserRoleDO::getUserId, userId);}

}
