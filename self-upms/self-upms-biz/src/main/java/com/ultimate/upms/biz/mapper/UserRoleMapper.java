package com.ultimate.upms.biz.mapper;

import com.ultimate.self.common.mybatis.core.mapper.BaseMapperX;
import com.ultimate.upms.api.dataobject.UserRoleDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/9/12 15:57
 */
@Mapper
public interface UserRoleMapper extends BaseMapperX<UserRoleDo> {

    default List<UserRoleDo> selectListByUserId(Long userId){return selectList(UserRoleDo::getUserId, userId);}

}
