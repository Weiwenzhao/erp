package com.hk.wwz.service;

import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.pojo.ResultBean;

public interface ApiConfigService {
    ResultBean add(ApiConfig apiConfig);

    ResultBean findAll();

    ResultBean deleteById(String id);
}
