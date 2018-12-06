package com.red.star.wechat.work.core.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // 资源需要的权限
        if (configAttributes == null) {
            return;
        }
        // 遍历需要的角色集合
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();
            String needRole = ca.getAttribute();
            // 遍历登录用户拥有权限
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                // 只要有一个权限匹配，则可访问
                if (needRole.equals(ga.getAuthority())) {
                    return;
                }
            }
        }

        // 抛出拒绝访问的异常
        throw new AccessDeniedException("no right");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
