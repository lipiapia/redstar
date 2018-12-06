package com.red.star.wechat.work.constant;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/30.
 * 响应状态码常量类
 */
public final class ResponseConstant {

    /**
     * 响应状态码:失败
     **/
    public static final Integer RESPONSE_CODE_ERROR = -1;

    /**
     * 响应消息:失败
     **/
    public static final String RESPONSE_MESSAGE_ERROR = "失败，请核对参数";

    /**
     * 响应状态码:成功
     **/
    public static final Integer RESPONSE_CODE_SUCCESS = 200;

    /**
     * 响应消息:成功
     **/
    public static final String RESPONSE_MESSAGE_SUCCESS = "成功";

    /**
     * 响应状态码:参数为空
     **/
    public static final Integer RESPONSE_CODE_PARAM_EMPTY = -2;

    /**
     * 响应消息:参数为空
     **/
    public static final String RESPONSE_MESSAGE_PARAM_EMPTY = "失败，请求参数不能为空: ";

    /**
     * 响应状态码:参数格式错误
     **/
    public static final Integer RESPONSE_CODE_PARAM_FORMAT_ERROR = -3;

    /**
     * 响应消息:参数格式错误
     **/
    public static final String RESPONSE_MESSAGE_PARAM_FORMAT_ERROR = "失败，请求参数格式错误: ";

    /**
     * 响应状态码:未授权
     **/
    public static final Integer RESPONSE_CODE_AUTH_ERROR = 403;

    /**
     * 响应消息:认证失败
     **/
    public static final String RESPONSE_MESSAGE_AUTH_ERROR = "签名认证失败,请检查后重试";

    /**
     * 响应状态码: 应用程序错误
     **/
    public static final Integer RESPONSE_CODE_NO_RESOURCE = 500;

    /**
     * 响应消息:未授权
     **/
    public static final String RESPONSE_MESSAGE_NO_RESOURCE = "无法查找到资源:";
}
