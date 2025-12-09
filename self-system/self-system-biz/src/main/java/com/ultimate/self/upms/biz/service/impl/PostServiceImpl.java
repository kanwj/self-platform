package com.ultimate.self.upms.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ultimate.self.upms.api.dataobject.PostDO;
import com.ultimate.self.upms.biz.mapper.PostMapper;
import com.ultimate.self.upms.biz.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 描述：岗位 service 实现类
 * 作者：kanwj
 * 日期：2025/12/3 15:35
 */
@Service
@Validated
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<PostDO> getPostList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return postMapper.selectByIds(ids);
    }
}
