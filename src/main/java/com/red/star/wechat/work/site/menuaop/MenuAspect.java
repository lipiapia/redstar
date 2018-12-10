package com.red.star.wechat.work.site.menuaop;

import com.red.star.wechat.work.entity.MenuResource;
import com.red.star.wechat.work.utils.MenuUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author liucancan
 * @description 动态菜单
 * @date 2018/12/7
 */
@Aspect
@Component
public class MenuAspect {

    @Pointcut("execution(public * com.red.star.wechat.work.site..*.*(..))")
    public void webMenu() {
    }

    @Before("webMenu()")
    public void deBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            HttpSession session = request.getSession();
            List<MenuResource> menus = (List<MenuResource>) session.getAttribute("menus");
            if (null != menus) {
                String resourceUri = request.getRequestURI();
                List<MenuResource> menuResources = MenuUtil.menusChecked(resourceUri, menus);
                session.setAttribute("menus", menuResources);
            }
        }
    }

    @Around("webMenu()")
    public Object arround(ProceedingJoinPoint pjp) {
        try {
            Object obj = pjp.proceed();
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}