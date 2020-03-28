package com.hk.wwz.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hk.wwz.dto.ConfigReqDto;
import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.pojo.ResponObj;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.ApiConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/config")
@RestController
@CrossOrigin
@Api(value = "apiConfig表")
public class ApiConfigController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    ApiConfigService apiConfigService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加或者更新apiConfig数据", notes = "添加或者更新apiConfig数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "appKey", value = "appKey", dataType = "String", required = true), @ApiImplicitParam(name = "appSecret", value = "appSecret", dataType = "String", required = true), @ApiImplicitParam(name = "companyId", value = "companyId", dataType = "Integer", required = false), @ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true), @ApiImplicitParam(name = "companyName", value = "companyName", dataType = "String", required = true)})
    public ResponObj<Integer> addApiConfig(@RequestBody ApiConfig apiConfig) {
        ResponObj<Integer> result = new ResponObj<>(-1,"添加失败");
        if(null == apiConfig || StringUtils.isBlank(apiConfig.getName()) || StringUtils.isBlank(apiConfig.getValue())){
            result.setMsg("不合法的参数");
            return result;
        }
        return apiConfigService.add(apiConfig);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam()})
    @ApiOperation(value = "获取所有apiConfig数据", notes = "获取所有apiConfig数据")
    public ResponObj<List<ApiConfig>> findAll(@RequestBody ConfigReqDto configReqDto) {
        ResponObj<List<ApiConfig>> result = new ResponObj<>(-1,"failed");
        result.setCode(0);
        if(null == configReqDto){
            result.setMsg("params is null");
            return result;
        }
        if(null == configReqDto.getPageNo() || 0 == configReqDto.getPageNo() ){
            configReqDto.setPageNo(1);
        }
        if(null == configReqDto.getPageSize() || 0 == configReqDto.getPageSize()){
            configReqDto.setPageSize(10);
        }
        return apiConfigService.find(configReqDto);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除apiConfig数据", notes = "根据id删除apiConfig数据")
    @ApiImplicitParam(name = "id", value = "数据主键id", dataType = "String", required = true)
    public ResultBean deleteById(@PathVariable String id) {
        logger.info("开始删除数据：" + id);
        return apiConfigService.deleteById(id);
    }

}
