package com.red.star.wechat.work.core.interceptor;

import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.work.constant.Constant;
import com.red.star.wechat.work.core.security.MyAccessDecisionManager;
import com.red.star.wechat.work.entity.Admin;
import com.red.star.wechat.work.entity.Role;
import com.red.star.wechat.work.site.admin.mapper.AdminMapper;
import com.red.star.wechat.work.utils.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class MyFilterSecurityInterceptor extends FilterSecurityInterceptor
        implements Filter {

    @Resource
    @Qualifier(value = "securityMetadataSource")
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Resource
    private AdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        try {
            boolean isSsoLogin = ssoLogin(request);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        invoke(fi);
    }

    @Override
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public void invoke(FilterInvocation fi) throws IOException,
            ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);//MyInvocationSecurityMetadataSource.getAttributes()方法
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);//MyAccessDecisionManager.decide()方法进行对比
        }
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Resource
    public void setAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    @Override
    public void setSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource newSource) {
        this.securityMetadataSource = newSource;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    /**
     * 单点登录入口
     * <p>
     * channel 为空则是微信后台登录，为market为全民营销登录
     *
     * @param req
     * @return
     * @throws IOException
     * @throws ExecutionException
     */
    private boolean ssoLogin(ServletRequest req) throws IOException, ExecutionException {
        return true;
    }

    /**
     * session管理
     *
     * @param admin
     * @param request
     */
    private void initUserDetail(Admin admin, HttpServletRequest request) {
        request.getSession().setAttribute("name", admin.getName());
        Authentication authentication = new UsernamePasswordAuthenticationToken(admin, "", admin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

    /**
     * 单点登录用户信息获取
     *
     * @param ssoSessionId
     * @param channel
     * @return
     * @throws IOException
     */
    private String configUser(String ssoSessionId, String channel) throws IOException {
        String user = "";
        if (CheckUtil.isEmpty(channel)) {
            //微信后台登录
            user = ApiUtil.loginUserFromApi(ssoSessionId);
        }  else {
            //不支持的单点登录
            return user;
        }
        return user;
    }

}
