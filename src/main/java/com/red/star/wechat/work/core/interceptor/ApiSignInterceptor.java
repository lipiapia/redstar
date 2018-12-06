package com.red.star.wechat.work.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.red.star.wechat.data.entity.ApiClient;
import com.red.star.wechat.data.mappers.ApiClientMapper;
import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.data.utils.HttpUtil;
import com.red.star.wechat.data.utils.MD5Util;
import com.red.star.wechat.data.utils.SignUtil;
import com.red.star.wechat.work.entity.Response;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/7/10.
 * 签名拦截apiClientMapper
 */
public class ApiSignInterceptor extends HandlerInterceptorAdapter {


    @Resource
    private ApiClientMapper apiClientMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        if (url.contains("/api/getEmployeeIdAndName")) {
            return true;
        }

        // 获取签名认证参数
        String appId = request.getHeader("app-id");
        String redstarNocestr = request.getHeader("redstar-nocestr");
        String redstarTimestamp = request.getHeader("redstar-timestamp");
        String redstarSign = request.getHeader("redstar-sign");
        // 校验认证参数是否完备
        if (!CheckUtil.isEmpty(redstarTimestamp) && !CheckUtil.isEmpty(redstarSign) && !CheckUtil.isEmpty(redstarSign)) {
            if (CheckUtil.isEmpty(appId)) {
                // 兼容老接口
                Map<String, String> param = HttpUtil.getParam(request);
                param.put("redstar-nocestr", redstarNocestr);
                param.put("redstar-timestamp", redstarTimestamp);
                String newSign = SignUtil.fetchRedStarSign(param);
                if (redstarSign.equals(newSign)) {
                    return true;
                }
            } else {
                // api签名认证
                ApiClient apiClient = apiClientMapper.findByAppId(appId);
                if (apiClient != null) {
                    // 验证签名
                    String serverSign = sign(redstarNocestr, redstarTimestamp, apiClient.getAppSecret());
                    if (serverSign != null && serverSign.equals(redstarSign)) {
                        return true;
                    }
                }
            }
        }
        // 接口签名认证失败
        response.getWriter().write(JSON.toJSONString(Response.buildAuthError()));
        return false;
    }

    private String sign(String nocestr, String timestamp, String secret) {
        StringBuilder builder = new StringBuilder();
        builder.append("redstar-nocestr=").append(nocestr).append("&");
        builder.append("redstar-timestamp=").append(timestamp).append("&");
        builder.append("api-secret=").append(secret);
        return MD5Util.encode(builder.toString()).toLowerCase();
    }

}
