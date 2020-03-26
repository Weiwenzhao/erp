package com.hk.wwz.pojo;

public class Department {
    private static final long serialVersionUID = 8822418148337943979L;
    private Boolean autoAddUser;
    private Boolean createDeptGroup;

    private Long id;

    private Boolean isFromUnionOrg;

    private String name;

    private Long parentid;

    private String sourceIdentifier;

    public Department() {
    }

    public Boolean getAutoAddUser() {
        return this.autoAddUser;
    }

    public void setAutoAddUser(Boolean autoAddUser) {
        this.autoAddUser = autoAddUser;
    }

    public Boolean getCreateDeptGroup() {
        return this.createDeptGroup;
    }

    public void setCreateDeptGroup(Boolean createDeptGroup) {
        this.createDeptGroup = createDeptGroup;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsFromUnionOrg() {
        return this.isFromUnionOrg;
    }

    public void setIsFromUnionOrg(Boolean isFromUnionOrg) {
        this.isFromUnionOrg = isFromUnionOrg;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentid() {
        return this.parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getSourceIdentifier() {
        return this.sourceIdentifier;
    }

    public void setSourceIdentifier(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }
}
