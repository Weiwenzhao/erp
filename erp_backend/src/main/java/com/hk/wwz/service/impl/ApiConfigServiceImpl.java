package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.wwz.dao.ApiConfigMapper;
import com.hk.wwz.dto.ConfigReqDto;
import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.ApiConfigService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ApiConfigServiceImpl implements ApiConfigService {

    @Resource
    ApiConfigMapper apiConfigDao;

    @Override
    public ResultBean add(ApiConfig apiConfig) {

//        ApiConfig oldApiConfig = apiConfigDao.findOneById(apiConfig.getId());
//
//
//        log.info("获取的老数据是：" + JSON.toJSONString(oldApiConfig));
//        ResultBean resultBean = new ResultBean();
//        Timestamp time = new Timestamp(System.currentTimeMillis());
//        if (oldApiConfig != null) {
//            apiConfig.setUpdateTime(time);
//            if (apiConfigDao.update(apiConfig) <= 0) {
//                resultBean.setMessage("fail");
//            } else {
//                resultBean.setMessage(JSON.toJSONString(apiConfig));
//            }
//        } else {
//            apiConfig.setId(UUID.randomUUID().toString().substring(0,31));
//            apiConfig.setCreateTime(time);
//            if (apiConfigDao.add(apiConfig) <= 0) {
//                resultBean.setMessage("fail");
//            } else {
//                resultBean.setMessage(JSON.toJSONString(apiConfig));
//            }
//        }
   //     return resultBean;
        return null;
    }

    @Override
    public List<ApiConfig> findAllByCompanyId(ConfigReqDto configReqDto) {
        try{
            QueryWrapper<ApiConfig> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("company_id",configReqDto.getCompanyId());
            Page page = new Page(configReqDto.getPageNo(),configReqDto.getPageSize());
            IPage<ApiConfig> records = apiConfigDao.selectPage(page,queryWrapper);
            List<ApiConfig> apiConfigList = records.getRecords();
            log.info("获取到的数据是：" + JSON.toJSONString(apiConfigList));
            return apiConfigList;
        }catch (Exception e){
            log.error("查询配置信息，异常：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultBean deleteById(String id) {
        return new ResultBean(200, apiConfigDao.deleteById(id));
    }
}
