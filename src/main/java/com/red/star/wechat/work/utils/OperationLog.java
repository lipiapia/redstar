package com.red.star.wechat.work.utils;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 操作行为日志记录
 *
 * @author chenlong
 * @Date 2018-8-1
 */
public class OperationLog {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(OperationLog.class);


    /**
     * 开始记录操作行为
     *
     * @param request
     */
    public static void begin(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        StringBuilder buf = new StringBuilder();
        for (String name : map.keySet()) {
            String[] values = map.get(name);
            buf.append(name).append("=").append(Arrays.toString(values)).append("&");
        }
        logger.warn("正在访问敏感信息，访问地址:{},访问者IP:{},访问参数:{}", request.getRequestURI(), getIpAddress(request), buf.toString());
    }


    /**
     * 记录返回数据
     *
     * @param obj
     */
    public static void end(Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] instanceof File) {
                logger.warn("正在导出敏感信息,导出文件地址:{}", "上传至文件服务器返回的文件url");
                break;
            } else if (obj[i] instanceof List) {
                //end
                logger.warn("正在查看敏感信息,查看条数:{}", ((List) obj[i]).size());
                break;
            }
        }
    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
