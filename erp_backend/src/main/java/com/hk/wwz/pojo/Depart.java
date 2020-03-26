package com.hk.wwz.pojo;

import com.dingtalk.api.response.OapiDepartmentGetResponse;

import java.sql.Timestamp;
import java.util.Objects;

public class Depart {
    private String id;
    private String indexCode;
    private String parentIndexCode;
    private String name;
    private String sourceIdentifier;
    private String departManageUserIdList;
    private Timestamp updateTime;

    public Depart() {
    }

    public Depart(String id, String indexCode, String parentIndexCode, String name, OapiDepartmentGetResponse oapiDepartmentGetResponse, Timestamp updateTime) {
        this.id = id;
        this.indexCode = indexCode;
        this.parentIndexCode = parentIndexCode;
        this.name = name;
        this.updateTime = updateTime;
        if (oapiDepartmentGetResponse != null && oapiDepartmentGetResponse.isSuccess()) {
            this.departManageUserIdList = oapiDepartmentGetResponse.getDeptManagerUseridList();
            this.sourceIdentifier = oapiDepartmentGetResponse.getSourceIdentifier();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depart depart = (Depart) o;
        return Objects.equals(indexCode, depart.indexCode) &&
                Objects.equals(parentIndexCode, depart.parentIndexCode) &&
                Objects.equals(name, depart.name) &&
                Objects.equals(sourceIdentifier, depart.sourceIdentifier) &&
                Objects.equals(departManageUserIdList, depart.departManageUserIdList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(indexCode, parentIndexCode, name, sourceIdentifier, departManageUserIdList);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getParentIndexCode() {
        return parentIndexCode;
    }

    public void setParentIndexCode(String parentIndexCode) {
        this.parentIndexCode = parentIndexCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceIdentifier() {
        return sourceIdentifier;
    }

    public void setSourceIdentifier(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }

    public String getDepartManageUserIdList() {
        return departManageUserIdList;
    }

    public void setDepartManageUserIdList(String departManageUserIdList) {
        this.departManageUserIdList = departManageUserIdList;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
