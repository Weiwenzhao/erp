package com.hk.wwz.pojo;

import java.sql.Timestamp;

public class ImpInfo {
    private String id;
    private String name;
    private String value;
    private String publicKey;
    private String privateKey;
    private Timestamp createTime;
    private Timestamp updateTime;

    public ImpInfo() {
    }

    public ImpInfo(String id, String name, String value, String publicKey, String privateKey, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
