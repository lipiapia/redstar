package com.red.star.wechat.work.base;

import lombok.Data;

/**
 * @param
 * @author liucancan
 * @description 统一返回格式
 * @return
 * @date 2018/12/5
 */

@Data
public class ResponseBase {

    private Integer rtnCode;
    private String msg;
    private Object data;

    public ResponseBase(Integer rtnCode, String msg, Object data) {
        super();
        this.rtnCode = rtnCode;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBase [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
    }

}
