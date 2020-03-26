package com.hk.wwz.pojo;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Login {
    private String id;
    private String userName;
    private String password;
    private Integer isLocked;
    private Timestamp createTime;
    private Timestamp updateTime;

}
