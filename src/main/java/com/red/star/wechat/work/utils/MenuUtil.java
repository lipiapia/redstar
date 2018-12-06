package com.red.star.wechat.work.utils;

import com.red.star.wechat.work.entity.MenuResource;

import java.util.List;

/**
 * @description: 修改菜单查看标识
 * @author: liucancan
 * @create: 2018-12-04 16:42
 **/
public class MenuUtil {
    public static List<MenuResource> menusChecked(String resourceUri, List<MenuResource> menuResources) {
        for (MenuResource menuResource : menuResources) {
            menuResource.setChecked(0);
            if (null != menuResource.getChilds() && menuResource.getChilds().size() > 0) {
                for (MenuResource childs : menuResource.getChilds()) {
                    if (childs.getAddress().equals(resourceUri)) {
                        childs.setChecked(1);
                        menuResource.setChecked(1);
                    } else {
                        childs.setChecked(0);
                    }
                }
            }
        }
        return menuResources;
    }
}
