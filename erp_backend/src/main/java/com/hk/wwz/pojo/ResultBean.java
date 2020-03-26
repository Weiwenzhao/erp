package com.hk.wwz.pojo;

public class ResultBean {
    private Integer code = 200;
    private Object message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ResultBean(Integer code, Object message) {
        this.code = code;
        this.message = message;
    }

    public ResultBean() {
    }
}
