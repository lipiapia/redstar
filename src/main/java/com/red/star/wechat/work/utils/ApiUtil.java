package com.red.star.wechat.work.utils;

import com.alibaba.fastjson.JSONObject;
import com.red.star.wechat.data.entity.SysParam;
import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.data.utils.HttpUtil;
import com.red.star.wechat.data.utils.SignUtil;
import com.red.star.wechat.data.utils.WeiXinUtil;
import com.red.star.wechat.work.constant.ApiConstant;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Yenan
 * @Description:
 * @Date: Created in 14:42 2018/1/31
 */
public class ApiUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiUtil.class);

    public static String getMallMessageApi(String path, String params) throws IOException {
        // path = path.trim();
        //path = path.replaceAll("[\\s]+", "%20");
        Request request = Request.Post(ApiConstant.MALL_API_URL_STAG);
        request.setHeader("Content-Type", "application/json");
        return HttpUtil.fetch(request, params);
    }

    public static String getMerchantByApi(String pageNum, String pageSize, String marketId) throws IOException {
        // path = path.trim();
        //path = path.replaceAll("[\\s]+", "%20");
        String path = ApiConstant.MERCHANT_API_URL_ZS + marketId;
        System.out.println(path);
        Request request = Request.Post(path);
        Map<String, String> param = new HashMap<>();
        param.put("page", pageNum);
        param.put("limit", pageSize);
        request.setHeader("Content-Type", "application/json");
        LOGGER.info("参数{}", param);
        return HttpUtil.fetch(request, JSONObject.toJSONString(param));
    }

    public static String loginUserFromApi(String key) throws IOException {
        Map<String, String> param = new HashMap<>();
        String sign = SignUtil.fetchSign(param);
        param.put("key", key);
        Request request = Request.Post(SysParam.MACALLINE_URL + "login/XcxCrmUnifiedLogin");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        return resp;
    }

    public static void main(String[] args) throws IOException {
        String s = loginUserFromApi("b00b06ba0ded40fa9151695c26dfc72e");
        System.err.println(s);
    }

    public static String loginUserFromQMYX(String ssoSessionId) throws IOException {
        String result = "";
        if (!CheckUtil.isEmpty(ssoSessionId)) {
            result = WeiXinUtil.getStaffInfo(ssoSessionId);
        }
        return result;
    }
}
