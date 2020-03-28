package com.hk.wwz.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApiConfig {
    private String id;
    private String name;
    private String value;
    private String companyId;
    private String remark;
    private Timestamp createTime;
    private Timestamp updateTime;
}
