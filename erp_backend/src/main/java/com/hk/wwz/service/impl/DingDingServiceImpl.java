package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentGetRequest;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserListbypageRequest;
import com.dingtalk.api.response.OapiDepartmentGetResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.hk.wwz.until.config.DingDingUrl;
import com.hk.wwz.dao.ApiConfigMapper;
import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.service.DingDingService;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DingDingServiceImpl implements DingDingService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ApiConfigMapper apiConfigDao;

    @Override
    public OapiGettokenResponse getAccessToken() throws ApiException {
        String id = "c3bef166-e0ef-4f3b-93e8-ea6b6df";
        ApiConfig apiConfig = apiConfigDao.findOneById(id);
        logger.info("获取公司的信息是：" + JSON.toJSONString(apiConfig));
        if (apiConfig != null) {
            DefaultDingTalkClient client = new DefaultDingTalkClient(DingDingUrl.ACCESS_TOKEN_URL.URL);
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(apiConfig.getAppKey());
            request.setAppsecret(apiConfig.getAppSecret());
            request.setHttpMethod("GET");
            return client.execute(request);
        }
        return null;
    }

    @Override
    public OapiDepartmentListResponse getAllDepartList(String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(DingDingUrl.DEPART_LIST_URL.URL);
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
//        request.setId("123");
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = client.execute(request, accessToken);
        return response;
    }

    @Override
    public OapiDepartmentGetResponse getDepartInfoById(Long id, String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(DingDingUrl.DEPART_INFO_URL.URL);
        OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
        request.setId(id + "");
        request.setHttpMethod("GET");
        return client.execute(request, accessToken);
    }

    @Override
    public OapiUserListbypageResponse getAllUserList(long begin, long end, long departId, String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(DingDingUrl.USER_LIST_URL.URL);
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(departId);
        request.setOffset(begin);
        request.setSize(end);
        request.setOrder("entry_desc");
        request.setHttpMethod("GET");
        return client.execute(request, accessToken);
    }

    @Override
    public OapiUserListbypageResponse getUserInfoById(Long id, String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(DingDingUrl.USER_INFO_URL.URL);
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(id);
        request.setOffset(0L);
        request.setSize(10L);
        request.setOrder("entry_desc");
        request.setHttpMethod("GET");
        OapiUserListbypageResponse execute = client.execute(request, accessToken);
        return null;
    }
}
