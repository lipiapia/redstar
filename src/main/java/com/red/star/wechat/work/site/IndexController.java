package com.red.star.wechat.work.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/26.
 */
@Controller
public class IndexController {

    @RequestMapping("/table")
    public String table() {
        return "table";
    }
}
