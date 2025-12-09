package com.ultimate.self.upms.biz.service;

import com.ultimate.self.upms.api.dataobject.PostDO;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * 描述：岗位 service 接口
 * 作者：kanwj
 * 日期：2025/12/3 15:34
 */
public interface PostService {

    /**
     * 描述：获得岗位列表
     * 作者：kanwj
     * 日期：2025/12/4 15:10
     */
    List<PostDO> getPostList(@Nullable Collection<Long> ids);
}
