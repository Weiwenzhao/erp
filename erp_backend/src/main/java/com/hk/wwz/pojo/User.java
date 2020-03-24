package com.hk.wwz.pojo;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private String id;
    private String userId;
    private String unionId;
    private String name;
    private String mobile;
    private Integer isAdmin;
    private Integer isBoss;
    private String dept;
    private String avatar;
    private Timestamp updateTime;

    public User(String id, String userId, String unionId, String name, String mobile, Integer isAdmin, Integer isBoss, String dept, String avatar, Timestamp updateTime) {
        this.id = id;
        this.userId = userId;
        this.unionId = unionId;
        this.name = name;
        this.mobile = mobile;
        this.isAdmin = isAdmin;
        this.isBoss = isBoss;
        this.dept = dept;
        this.avatar = avatar;
        this.updateTime = updateTime;
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(unionId, user.unionId) &&
                Objects.equals(name, user.name) &&
                Objects.equals(mobile, user.mobile) &&
                Objects.equals(isAdmin, user.isAdmin) &&
                Objects.equals(isBoss, user.isBoss) &&
                Objects.equals(dept, user.dept) &&
                Objects.equals(avatar, user.avatar);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, unionId, name, mobile, isAdmin, isBoss, dept, avatar);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(Integer isBoss) {
        this.isBoss = isBoss;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
