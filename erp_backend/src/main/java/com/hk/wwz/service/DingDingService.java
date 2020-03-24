package com.hk.wwz.service;

import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;

public interface DingDingService {
    OapiGettokenResponse getAccessToken() throws ApiException;

    OapiDepartmentListResponse getAllDepartList(String accessToken) throws ApiException;

    OapiDepartmentGetResponse getDepartInfoById(Long id, String accessToken) throws ApiException;

    OapiUserListbypageResponse getAllUserList(long begin, long end, long departId, String accessToken) throws ApiException;

    OapiUserListbypageResponse getUserInfoById(Long id, String accessToken) throws ApiException;
}
