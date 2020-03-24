package com.hk.wwz.dao;

import com.hk.wwz.pojo.ApiConfig;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ApiConfigMapper {
    ApiConfig findOneById(String id);

    int add(ApiConfig apiConfig);

    int update(ApiConfig apiConfig);

    List<ApiConfig> findAll();

    int deleteById(String id);
}
