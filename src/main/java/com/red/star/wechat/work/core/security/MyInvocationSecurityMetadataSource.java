package com.red.star.wechat.work.core.security;

import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.work.site.admin.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "securityMetadataSource")
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    @Qualifier(value = "resService")
    private ResourceService resourceService;

    /**
     * 资源角色全局映射表
     */
    public static Map<String, Collection<ConfigAttribute>> resourceMap = new LinkedHashMap<>();

    private UrlMatcher urlMatcher = new AntUrlPathMatcher();

    public MyInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    private void loadResourceDefine() {
        resourceMap = new HashMap<>();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        if (CheckUtil.isEmpty(resourceMap)) {
            resourceMap = resourceService.getResourceMap();
        }
        String address = ((FilterInvocation) object).getRequestUrl();
        int firstQuestionMarkIndex = address.indexOf(("?"));
        //如果请求的资源路径有？后面的参数，则将？后面的切掉，以免拒绝访问
        if (firstQuestionMarkIndex != -1) {
            address = address.substring(0, firstQuestionMarkIndex);
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        List<ConfigAttribute> result = new ArrayList<>();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (urlMatcher.pathMatchesUrl(resURL, address)) {
                result.addAll(resourceMap.get(resURL));
            }
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        if (!CheckUtil.isEmpty(result)) {
            return result;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // TODO: 2018/1/29 spring初始化问题 导致flyway脚本在该方法后执行
        return null;
    }

}
