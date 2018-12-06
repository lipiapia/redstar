package com.red.star.wechat.work.site.admin.service;

import com.red.star.wechat.work.entity.Resource;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/29.
 * 资源服务接口
 */
public interface ResourceService {

    /**
     * 获取资源角色映射表
     *
     * @return
     */
    Map<String, Collection<ConfigAttribute>> getResourceMap();

    /**
     * 查询所有资源列表，关联查询所需角色
     *
     * @return
     */
    List<Resource> findAll();

}
