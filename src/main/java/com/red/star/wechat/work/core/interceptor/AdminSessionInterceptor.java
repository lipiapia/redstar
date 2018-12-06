package com.red.star.wechat.work.core.interceptor;

import com.red.star.wechat.data.entity.SysParam;
import com.red.star.wechat.data.entity.WorkAccessLog;
import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.work.constant.AdminSessionHolder;
import com.red.star.wechat.work.entity.Admin;
import com.red.star.wechat.work.site.log.LogService;
import com.red.star.wechat.work.utils.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * PACKAGE      :  com.project.app.api.interceptor
 * CREATE DATE  :  2015/11/25
 * AUTHOR       :  xiaoyi.xie
 * 文件描述     :  一次性token拦截
 */
public class AdminSessionInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        Admin admin = null;
        if (!CheckUtil.isEmpty(securityContextImpl)) {
            admin = (Admin) securityContextImpl.getAuthentication().getPrincipal();
        }
        if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/work/validateCode") || request.getRequestURI().contains("/work/checkCode") || request.getRequestURI().contains("/api/*")) {
            WorkAccessLog workAccessLog = LogUtil.addViewLog(request, admin, applicationName);
            logService.addWorkAccessLog(workAccessLog);
            return true;
        }
        if (CheckUtil.isEmpty(admin) || CheckUtil.isEmpty(admin.getId())) {
            response.sendRedirect(SysParam.MACALLINE_LOGOUT_URL);
            return false;
        }
        WorkAccessLog workAccessLog = LogUtil.addViewLog(request, admin, applicationName);
        logService.addWorkAccessLog(workAccessLog);
        AdminSessionHolder.put(admin);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        long start = (Long) request.getAttribute("start");
        long end = System.currentTimeMillis();
        System.out.println("请求时间====>");
        System.out.println(end - start);
    }

}