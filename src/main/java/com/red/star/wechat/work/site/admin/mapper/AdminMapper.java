package com.red.star.wechat.work.site.admin.mapper;


import com.red.star.wechat.data.core.base.MyMapper;
import com.red.star.wechat.work.entity.Admin;
import com.red.star.wechat.work.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * Created by xulonglong on 2017/6/15.
 */
public interface AdminMapper extends MyMapper<Admin> {

    Admin findAdminByUsername(String username);

    List<Admin> findAll();

}
