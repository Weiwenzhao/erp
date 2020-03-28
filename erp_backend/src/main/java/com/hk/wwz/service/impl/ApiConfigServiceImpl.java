package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.wwz.dao.ApiConfigMapper;
import com.hk.wwz.dto.ConfigReqDto;
import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.pojo.ResponObj;
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
    public ResponObj<Integer> add(ApiConfig apiConfig) {
        ResponObj<Integer> result = new ResponObj<>(-1,"添加失败");
        try{
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("company_id",apiConfig.getCompanyId());
            queryWrapper.eq("name",apiConfig.getName());
            ApiConfig config = apiConfigDao.selectOne(queryWrapper);
            if(null != config){
                result.setMsg("配置项已经存在!");
                return result;
            }
            apiConfig.setCreateTime(new Timestamp(System.currentTimeMillis()));
            int tmp = apiConfigDao.insert(apiConfig);
            result.setCode(0);
            result.setMsg("success");
            result.setData(tmp);
            return result;
        }catch (Exception e){
            log.error("添加系统属性配置异常：" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResponObj<List<ApiConfig>> find(ConfigReqDto configReqDto) {
        ResponObj<List<ApiConfig>> resp = new ResponObj<>(-1,"获取系统属性异常");
        try{
            QueryWrapper<ApiConfig> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("company_id",configReqDto.getCompanyId());
            Page page = new Page(configReqDto.getPageNo(),configReqDto.getPageSize());
            IPage<ApiConfig> records = apiConfigDao.selectPage(page,queryWrapper);
            List<ApiConfig> apiConfigList = records.getRecords();
            log.info("获取到的数据是：" + JSON.toJSONString(apiConfigList));
            resp.setCode(0);
            resp.setMsg("success");
            resp.setData(apiConfigList);
            return resp;
        }catch (Exception e){
            log.error("查询配置信息，异常：" + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public ResultBean deleteById(String id) {
        return new ResultBean(200, apiConfigDao.deleteById(id));
    }
}
