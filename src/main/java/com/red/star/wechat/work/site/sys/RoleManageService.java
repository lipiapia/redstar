package com.red.star.wechat.work.site.sys;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.red.star.wechat.data.entity.vo.RoleInfo;
import com.red.star.wechat.data.mappers.RoleManageMapper;
import com.red.star.wechat.work.entity.TableContainer;
import com.red.star.wechat.work.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RoleManageService {

    @Resource
    private RoleManageMapper roleManageMapper;

    public TableContainer queryRoleInfo(RoleInfo roleInfo) {
        Page<?> page = PageHelper.startPage(roleInfo.getPage(),roleInfo.getRows());
        List<RoleInfo> list = roleManageMapper.selectRoleInfo();
        return CommonUtil.backTableContainer(list, page);
    }

    public Integer changeRoleStatus(RoleInfo roleInfo) {
        return  roleManageMapper.updateByPrimaryKeySelective(roleInfo);
    }

    public Integer addRole(RoleInfo roleInfo) {
        roleInfo.setCreateTime(new Date());
        return roleManageMapper.insert(roleInfo);
    }
}
