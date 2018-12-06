package com.red.star.wechat.work.site.log;

import com.alibaba.fastjson.JSON;
import com.red.star.wechat.data.entity.WorkAccessLog;
import com.red.star.wechat.data.entity.WorkErrorLog;
import com.red.star.wechat.data.mappers.WorkAccessLogMapper;
import com.red.star.wechat.data.mappers.WorkErrorLogMapper;
import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.data.utils.HttpUtil;
import com.red.star.wechat.work.constant.AdminSessionHolder;
import com.red.star.wechat.work.entity.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Service
public class LogService {


    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private WorkAccessLogMapper workAccessLogMapper;

    @Resource
    private WorkErrorLogMapper workErrorLogMapper;

    @Async
    public void addWorkAccessLog(WorkAccessLog workAccessLog) {
        workAccessLogMapper.insert(workAccessLog);
    }

    @Async
    public void addWorkErrorLog(HttpServletRequest request, String exceptionTrace) {
        Admin admin = AdminSessionHolder.get();

        WorkErrorLog workErrorLog = new WorkErrorLog();
        workErrorLog.setIp(HttpUtil.getIpAddr(request));
        workErrorLog.setServer(applicationName);
        workErrorLog.setContent(exceptionTrace);
        Map<String, String> param = HttpUtil.getParam(request);
        String body = JSON.toJSONString(param);
        workErrorLog.setMemo(body);
        if (!CheckUtil.isEmpty(request.getQueryString())) {
            workErrorLog.setUrl(request.getRequestURI() + "?" + request.getQueryString());
        } else {
            workErrorLog.setUrl(request.getRequestURI());
        }
        if (!CheckUtil.isEmpty(admin)) {
            workErrorLog.setAdminId(admin.getId());
            workErrorLog.setAdminName(admin.getName());
        }
        workErrorLog.setTime(new Date());
        workErrorLogMapper.insert(workErrorLog);
    }
}
