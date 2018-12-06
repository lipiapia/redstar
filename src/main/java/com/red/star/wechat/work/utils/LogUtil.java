package com.red.star.wechat.work.utils;

import com.alibaba.fastjson.JSON;
import com.red.star.wechat.data.entity.WorkAccessLog;
import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.data.utils.HttpUtil;
import com.red.star.wechat.work.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

public class LogUtil {
	/**
	 * 组合请求内容
	 *
	 * @param request
	 * @return
	 * @throws UnknownHostException
	 */
	public static WorkAccessLog addViewLog(HttpServletRequest request, Admin admin, String applicationName) throws
			UnknownHostException {
		WorkAccessLog workAccessLog = new WorkAccessLog();
		Map<String, String> param = HttpUtil.getParam(request);
		String body = JSON.toJSONString(param);
		workAccessLog.setMemo(body);
		workAccessLog.setServer(applicationName);
		workAccessLog.setIp(HttpUtil.getIpAddr(request));
		workAccessLog.setUa(request.getHeader("User-Agent"));
		if (!CheckUtil.isEmpty(request.getQueryString())) {
			workAccessLog.setUrl(request.getRequestURI() + "?" + request.getQueryString());
		} else {
			workAccessLog.setUrl(request.getRequestURI());
		}
		workAccessLog.setTime(new Date());
		if (!CheckUtil.isEmpty(admin)) {
			workAccessLog.setAdminId(admin.getId());
			workAccessLog.setAdminName(admin.getName());
		}
		return workAccessLog;
	}
}
