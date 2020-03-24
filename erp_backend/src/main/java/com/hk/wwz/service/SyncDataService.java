package com.hk.wwz.service;

import com.hk.wwz.pojo.ResultBean;
import com.taobao.api.ApiException;

public interface SyncDataService {
    ResultBean syncDepartData() throws ApiException;

    ResultBean syncUser() throws ApiException;

    ResultBean syncInfo() throws ApiException;
}
