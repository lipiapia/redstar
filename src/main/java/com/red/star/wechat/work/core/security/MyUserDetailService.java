package com.red.star.wechat.work.core.security;

import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.work.entity.Admin;
import com.red.star.wechat.work.site.admin.service.AdminService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findAdminByUsername(username);
        if (CheckUtil.isEmpty(admin)) {
            throw new UsernameNotFoundException("用户名" + username + "不存在");
        }
        return admin;
    }

    private List<GrantedAuthority> buildUserAuthority(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>(0);
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }


}
