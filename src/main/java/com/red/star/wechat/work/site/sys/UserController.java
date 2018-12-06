package com.red.star.wechat.work.site.sys;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: 用户控制器
 * @author: liucancan
 * @create: 2018-12-03 10:41
 **/
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @GetMapping()
    public ModelAndView userManageView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
