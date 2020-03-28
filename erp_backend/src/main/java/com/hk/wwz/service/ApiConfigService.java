package com.hk.wwz.service;

import com.hk.wwz.dto.ConfigReqDto;
import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.pojo.ResponObj;
import com.hk.wwz.pojo.ResultBean;

import java.util.List;

public interface ApiConfigService {
    ResponObj<Integer> add(ApiConfig apiConfig);

    ResponObj<List<ApiConfig>> find(ConfigReqDto configReqDto);

    ResultBean deleteById(String id);
}
