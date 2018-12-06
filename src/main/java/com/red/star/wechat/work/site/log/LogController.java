package com.red.star.wechat.work.site.log;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: 日志控制器
 * @author: liucancan
 * @create: 2018-12-03 10:41
 **/
@RestController
@RequestMapping("/sys/log")
public class LogController {

    @GetMapping()
    public ModelAndView logView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("log");
        return modelAndView;
    }
}
