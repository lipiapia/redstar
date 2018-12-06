package com.red.star.wechat.work.site.admin.mapper;

import com.red.star.wechat.data.core.base.MyMapper;
import com.red.star.wechat.work.entity.Resource;
import com.red.star.wechat.work.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/26.
 */
public interface RoleMapper extends MyMapper<Role> {

    /**
     * 查询所有角色，关联查询资源
     * @return
     */
    List<Role> findAll();

    Set<Resource> findResourceByRole(Integer id);

    Set<Resource> findResourceByRoleId(Integer id);

    void deleteRoleResourceByRoleId(Integer id);

    void addRoleResource(@Param("roleId") Integer roleId, @Param("resourceId") Integer resourceId);

    List<Role> findAdminRoleById(Integer id);
}
