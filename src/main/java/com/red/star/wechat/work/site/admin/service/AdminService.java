package com.red.star.wechat.work.site.admin.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.red.star.wechat.work.entity.Admin;
import com.red.star.wechat.work.entity.TableContainer;
import com.red.star.wechat.work.site.admin.mapper.AdminMapper;
import com.red.star.wechat.work.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xulonglong on 2017/6/15.
 */
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public Admin findAdminByUsername(String username) {
        return adminMapper.findAdminByUsername(username);
    }

    public List<Admin> findAll() {
        return adminMapper.findAll();
    }

    public TableContainer findAll(Admin admin) {
        Page<?> page = PageHelper.startPage(admin.getPage(), admin.getRows());
        List<Admin> adminList = adminMapper.selectAll();
        return CommonUtil.backTableContainer(adminList, page);
    }
}
