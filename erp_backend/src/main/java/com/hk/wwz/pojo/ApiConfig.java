package com.hk.wwz.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApiConfig {
    private String id;
    private String appKey;
    private String appSecret;
    private String companyId;
    private String companyName;
    private Timestamp createTime;
    private Timestamp updateTime;
}
