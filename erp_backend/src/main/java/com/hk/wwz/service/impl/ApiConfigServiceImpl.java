package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.hk.wwz.dao.ApiConfigMapper;
import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.ApiConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class ApiConfigServiceImpl implements ApiConfigService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ApiConfigMapper apiConfigDao;

    @Override
    public ResultBean add(ApiConfig apiConfig) {
        ApiConfig oldApiConfig = apiConfigDao.findOneById(apiConfig.getId());
        logger.info("获取的老数据是：" + JSON.toJSONString(oldApiConfig));
        ResultBean resultBean = new ResultBean();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (oldApiConfig != null) {
            apiConfig.setUpdateTime(time);
            if (apiConfigDao.update(apiConfig) <= 0) {
                resultBean.setMessage("fail");
            } else {
                resultBean.setMessage(JSON.toJSONString(apiConfig));
            }
        } else {
            apiConfig.setId(UUID.randomUUID().toString().substring(0,31));
            apiConfig.setCreateTime(time);
            if (apiConfigDao.add(apiConfig) <= 0) {
                resultBean.setMessage("fail");
            } else {
                resultBean.setMessage(JSON.toJSONString(apiConfig));
            }
        }
        return resultBean;
    }

    @Override
    public ResultBean findAll() {
        List<ApiConfig> apiConfigList = apiConfigDao.findAll();
        logger.info("获取到的数据是：" + JSON.toJSONString(apiConfigList));

        return new ResultBean(200, JSON.toJSONString(apiConfigList));
    }

    @Override
    public ResultBean deleteById(String id) {
        return new ResultBean(200, apiConfigDao.deleteById(id));
    }
}
