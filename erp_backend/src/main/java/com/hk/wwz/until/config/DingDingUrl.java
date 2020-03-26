package com.hk.wwz.until.config;

public enum DingDingUrl {
    ACCESS_TOKEN_URL("获取鉴权信息URL", "https://oapi.dingtalk.com/gettoken"),
    DEPART_LIST_URL("获取全部部门信息URL", "https://oapi.dingtalk.com/department/list"),
    DEPART_INFO_URL("获取单个部门详细信息URL", "https://oapi.dingtalk.com/department/get"),
    USER_LIST_URL("获取所有人员信息URL", "https://oapi.dingtalk.com/user/listbypage"),
    USER_INFO_URL("获取单个人员详细信息URL", "https://oapi.dingtalk.com/user/listbypage");

    public String KEY;
    public String URL;

    DingDingUrl(String KEY, String URL) {
        this.KEY = KEY;
        this.URL = URL;
    }
}
