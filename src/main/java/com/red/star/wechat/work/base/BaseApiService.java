package com.red.star.wechat.work.base;

import com.red.star.wechat.work.constant.ResponseConstant;

/**
 * @param
 * @author liucancan
 * @description 通用返回数据类型
 * @return
 * @date 2018/12/5
 */
public class BaseApiService {

    public ResponseBase setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    // 返回错误，可以传msg
    public ResponseBase setResultError(String msg) {
        return setResult(ResponseConstant.RESPONSE_CODE_NO_RESOURCE, msg, null);
    }

    // 返回成功，可以传data值
    public ResponseBase setResultSuccess(Object data) {
        return setResult(ResponseConstant.RESPONSE_CODE_SUCCESS, "处理成功", data);
    }

    // 返回成功，沒有data值
    public ResponseBase setResultSuccess() {
        return setResult(ResponseConstant.RESPONSE_CODE_SUCCESS, "处理成功", null);
    }

    // 返回成功，沒有data值
    public ResponseBase setResultSuccess(String msg) {
        return setResult(ResponseConstant.RESPONSE_CODE_SUCCESS, msg, null);
    }

    // 通用封装
    public ResponseBase setResult(Integer code, String msg, Object data) {
        return new ResponseBase(code, msg, data);
    }

    public static void main(String[] args) {

    }

}
