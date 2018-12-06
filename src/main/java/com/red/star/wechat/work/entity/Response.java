package com.red.star.wechat.work.entity;

import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.work.constant.ResponseConstant;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/30.
 * 接口响应实体
 */
@Data
public class Response {

    /**
     * 响应码
     **/
    private Integer code;
    /**
     * 响应消息（错误消息）
     **/
    private String msg;

    /**
     * 响应数据
     **/
    private Object data;

    public static Response buildErrorResponse() {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_ERROR)
                .msg(ResponseConstant.RESPONSE_MESSAGE_ERROR)
                .build();
    }

    public static Response buildErrorResponse(String msg) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_ERROR)
                .msg(msg)
                .build();
    }

    public static Response buildSuccessResponse() {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_SUCCESS)
                .msg(ResponseConstant.RESPONSE_MESSAGE_SUCCESS)
                .build();
    }

    public static Response buildSuccessResponse(Object data) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_SUCCESS)
                .msg(ResponseConstant.RESPONSE_MESSAGE_SUCCESS)
                .data(data)
                .build();
    }

    public static Response buildSuccessResponse(String key, Object data) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_SUCCESS)
                .msg(ResponseConstant.RESPONSE_MESSAGE_SUCCESS)
                .data(key, data)
                .build();
    }

    public static Response buildParamEmptyError(String param) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_PARAM_EMPTY)
                .msg(ResponseConstant.RESPONSE_MESSAGE_PARAM_EMPTY + param)
                .build();
    }

    public static Response buildParamFormatError(String param) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_PARAM_FORMAT_ERROR)
                .msg(ResponseConstant.RESPONSE_MESSAGE_PARAM_FORMAT_ERROR + param)
                .build();
    }

    /**
     * token为空或者从token中拿数据失败，一定要返回这个Response
     *
     * @return
     */
    public static Response buildAuthError() {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_AUTH_ERROR)
                .msg(ResponseConstant.RESPONSE_MESSAGE_AUTH_ERROR)
                .build();
    }

    public static Response buildNoResourceError(String resource) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_NO_RESOURCE)
                .msg(ResponseConstant.RESPONSE_MESSAGE_NO_RESOURCE + resource)
                .build();
    }

    public static class Builder {

        private Integer code;
        private String msg;
        private Map data;

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder data(String key, Object data) {
            if (this.data == null) {
                this.data = new HashMap<>();
            }
            this.data.put(key, data);
            return this;
        }

        public Builder data(Object data) {
            if (CheckUtil.isEmpty(data)) {
                return this;
            }

            if (this.data == null) {
                this.data = new HashMap<>();
            }

            if (data instanceof Map) {
                this.data.putAll((Map) data);
                return this;
            }
            String key = "";
            if (data instanceof List) {
                key = getSimpleName(((List) data).get(0).getClass());
            } else {
                key = getSimpleName(data.getClass());
            }
            if (!CheckUtil.isEmpty(key)) {
                this.data.put(key, data);
            }
            return this;
        }

        public Response build() {
            Response response = new Response();
            response.code = this.code;
            response.msg = this.msg;
            response.data = this.data;
            return response;
        }
    }


    private static String getSimpleName(Class clazz) {
        return clazz.getSimpleName().toLowerCase();
    }
}
