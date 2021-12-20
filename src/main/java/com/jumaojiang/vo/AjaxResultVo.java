package com.jumaojiang.vo;

import java.util.List;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/12
 */
public class AjaxResultVo<T> {
    private Integer code;   // 返回的状态码
    private String msg;     // 返回的信息(一般是错误信息或异常信息)
    private Object obj;     // 返回的数据可能是对象

    public AjaxResultVo() {
        this.code = 200;
        this.msg = "ok";
        this.obj = null;
    }

    public AjaxResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResultVo(Integer code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
