package com.red.star.wechat.work.site;

import com.red.star.wechat.work.entity.Response;
import com.red.star.wechat.work.site.log.LogService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/29.
 * 异常处理，跳转到错误页面
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @Resource
    private LogService logService;

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        String url = request.getRequestURL().toString();
        if (url.contains("/api")) {
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
            mav.addObject(Response.buildNoResourceError("500:服务器异常"));
            return mav;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        if (e instanceof NoHandlerFoundException) {
            mav.setViewName("error/404");
        } else {
            mav.setViewName("error/500");
        }
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);
        String exceptionTrace = writer.toString();
        logService.addWorkErrorLog(request, exceptionTrace);
        return mav;
    }

}
