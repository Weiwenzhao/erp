package com.hk.wwz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Role {
    @TableId(type = IdType.UUID)
    private String id;
    private String role;
    private String name;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String companyId;
}
