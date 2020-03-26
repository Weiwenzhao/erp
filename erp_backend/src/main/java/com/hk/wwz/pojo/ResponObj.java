package com.hk.wwz.pojo;

import lombok.Data;

@Data
public class ResponObj<T> {

    private Integer code;
    private String msg;
    private T data;
    public  ResponObj(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
