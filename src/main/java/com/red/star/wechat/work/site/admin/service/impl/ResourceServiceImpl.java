package com.red.star.wechat.work.site.admin.service.impl;

import com.red.star.wechat.work.entity.Resource;
import com.red.star.wechat.work.entity.Role;
import com.red.star.wechat.work.site.admin.mapper.ResourceMapper;
import com.red.star.wechat.work.site.admin.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/29.
 */
@Service("resService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Map<String, Collection<ConfigAttribute>> getResourceMap() {
        Map<String, Collection<ConfigAttribute>> result = new HashMap<>();
        List<Resource> resources = resourceMapper.findAll();
        for (Resource resource : resources) {
            Set<ConfigAttribute> itemAttributes = new HashSet<>();
            for (Role role : resource.getRoles()) {
                ConfigAttribute ca = new SecurityConfig(role.getAuthority());
                itemAttributes.add(ca);
            }
            result.put(resource.getAddress(), itemAttributes);
        }
        return result;
    }

    @Override
    public List<Resource> findAll() {
        return resourceMapper.findAll();
    }

}
