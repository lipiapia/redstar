package com.red.star.wechat.work.site.admin.mapper;

import com.red.star.wechat.data.core.base.MyMapper;
import com.red.star.wechat.work.entity.MenuResource;
import com.red.star.wechat.work.entity.Resource;

import java.util.List;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/26.
 */
public interface ResourceMapper extends MyMapper<Resource> {

    /**
     * 查询所有资源，关联查询所需角色
     *
     * @return
     */
    List<Resource> findAll();

    void deleteRoleResourceByResourceId(Integer id);

    /**
     * 登录者所拥有资源
     *
     * @param id [用户id]
     * @return java.util.List<com.red.star.wechat.work.entity.MenuResource>
     * @author liucancan
     * @date 18:12 2018/12/4
     */
    List<MenuResource> findResourceByUserId(Integer id);

}
