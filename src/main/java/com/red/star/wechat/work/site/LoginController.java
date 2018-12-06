package com.red.star.wechat.work.site;

import com.red.star.wechat.data.entity.SysParam;
import com.red.star.wechat.data.utils.ValidateCode;
import com.red.star.wechat.work.constant.AdminSessionHolder;
import com.red.star.wechat.work.entity.Admin;
import com.red.star.wechat.work.entity.MenuResource;
import com.red.star.wechat.work.site.admin.mapper.ResourceMapper;
import com.red.star.wechat.work.utils.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by xulonglong on 2018/1/25.
 */
@Controller
@Api("LoginController相关api")
public class LoginController {

    @Autowired
    private ResourceMapper resourceMapper;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        Object savedRequestObject = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequestObject != null) {
            request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
        }
        return "login";
    }

    @RequestMapping({"/deniedAccess"})
    public String deniedAccess() {
        return "error/403";
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        Object savedRequestObject = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequestObject != null) {
            request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
        }
        response.sendRedirect(SysParam.MACALLINE_LOGOUT_URL);
    }

    @RequestMapping("/work/home")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Admin admin = AdminSessionHolder.get();
        request.getSession().setAttribute("name", admin.getName());
        request.getSession().setAttribute("admin", admin);
        modelAndView.setViewName("home");
        // 登陆者拥有资源
        List<MenuResource> menus = resourceMapper.findResourceByUserId(admin.getId());
        modelAndView.addObject("menus", menus);
        request.getSession().setAttribute("menus", menus);
        return modelAndView;
    }

    /**
     * XCXGY 2018/4/13
     * 验证码
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public String validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        ValidateCode vCode = new ValidateCode(120, 40, 4, 50);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        return null;
    }

    /**
     * XCXGY 2018/4/17
     */
    @ResponseBody
    @RequestMapping(value = "/work/checkCode", method = RequestMethod.GET)
    public String checkCode(HttpServletRequest request) {
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("code");
        if (!StringUtils.equalsIgnoreCase(sessionCode, code)) {
            return "errs";
        } else {
            return "success";
        }
    }

    /**
     * 用于生成带四位数字验证码的图片
     */
    @RequestMapping(value = "/work/validateCode")
    @ResponseBody
    public String imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        //返回验证码和图片的map
        Map<String, Object> map = Captcha.getImageCode(86, 37, os);
        String simpleCaptcha = "code";
        request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
        request.getSession().setAttribute("codeTime", System.currentTimeMillis());
        try {
            ImageIO.write((BufferedImage) map.get("image"), "jpg", os);
        } catch (IOException e) {
            return "";
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
        return null;
    }
}
