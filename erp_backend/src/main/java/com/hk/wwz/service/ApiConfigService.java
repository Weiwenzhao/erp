package com.hk.wwz.service;

import com.hk.wwz.dto.ConfigReqDto;
import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.pojo.ResultBean;

import java.util.List;

public interface ApiConfigService {
    ResultBean add(ApiConfig apiConfig);

    List<ApiConfig> findAllByCompanyId(ConfigReqDto configReqDto);

    ResultBean deleteById(String id);
}
