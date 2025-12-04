package com.ultimate.upms.biz.convert;

import com.ultimate.self.common.framework.util.object.BeanUtils;
import com.ultimate.upms.api.dataobject.AdminUserDO;
import com.ultimate.upms.api.dataobject.DeptDO;
import com.ultimate.upms.api.dataobject.PostDO;
import com.ultimate.upms.api.dataobject.RoleDO;
import com.ultimate.upms.api.vo.DeptSimpleRespVO;
import com.ultimate.upms.api.vo.PostSimpleRespVO;
import com.ultimate.upms.api.vo.RoleSimpleRespVO;
import com.ultimate.upms.api.vo.UserProfileRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/12/3 9:26
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    default UserProfileRespVO convert(AdminUserDO user, List<RoleDO> userRoles,
                                      DeptDO dept, List<PostDO> posts) {
        UserProfileRespVO userVO = BeanUtils.toBean(user, UserProfileRespVO.class);
        userVO.setRoles(BeanUtils.toBean(userRoles, RoleSimpleRespVO.class));
        userVO.setDept(BeanUtils.toBean(dept, DeptSimpleRespVO.class));
        userVO.setPosts(BeanUtils.toBean(posts, PostSimpleRespVO.class));
        return userVO;
    }
}
