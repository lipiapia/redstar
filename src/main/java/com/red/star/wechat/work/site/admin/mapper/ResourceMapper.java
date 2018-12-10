package com.red.star.wechat.work.site.admin.mapper;

import com.red.star.wechat.data.core.base.MyMapper;
import com.red.star.wechat.work.entity.MenuResource;
import com.red.star.wechat.work.entity.Resource;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 查询菜单信息
     *
     * @param id 菜单或目录id
     * @return java.util.List<com.red.star.wechat.work.entity.MenuResource>
     * @author liucancan
     * @date 11:27 2018/12/10
     */
    @Select("SELECT t0_tr.id, t0_tr.pid, t0_tr.NAME, t0_tr.address, t0_tr.type , t0_tr.del, t1_tr.id AS 'parent.id', t1_tr.pid AS 'parent.pid', t1_tr.address AS 'parent.address', t1_tr.NAME AS 'parent.NAME' , t1_tr.type AS 'parent.type', t1_tr.del AS 'parent.del' FROM t_resource t0_tr LEFT JOIN t_resource t1_tr ON t1_tr.id = t0_tr.pid WHERE t0_tr.id = #{id}")
    MenuResource findResourceByid(Integer id);

}
