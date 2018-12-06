package com.red.star.wechat.work.site.sys;

import com.red.star.wechat.work.base.BaseApiService;
import com.red.star.wechat.work.base.ResponseBase;
import com.red.star.wechat.work.constant.AdminSessionHolder;
import com.red.star.wechat.work.entity.Admin;
import com.red.star.wechat.work.entity.MenuResource;
import com.red.star.wechat.work.site.admin.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @description: 菜单控制器
 * @author: liucancan
 * @create: 2018-12-03 10:41
 **/
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseApiService {

    @Autowired
    private ResourceMapper resourceMapper;

    @GetMapping()
    public ModelAndView menuViews() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu");
        return modelAndView;
    }

    @GetMapping("/menuList")
    public ResponseBase menuList() {
        Admin admin = AdminSessionHolder.get();
        List<MenuResource> menus = resourceMapper.findResourceByUserId(admin.getId());
        return setResultSuccess(menus);
    }
}
