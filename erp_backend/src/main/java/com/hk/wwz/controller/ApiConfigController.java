package com.hk.wwz.controller;

import com.hk.wwz.pojo.ApiConfig;
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

@RequestMapping("/hk/wwz/apiConfig")

@RestController
@CrossOrigin
@Api(value = "apiConfig表")
public class ApiConfigController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ApiConfigService apiConfigService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加或者更新apiConfig数据", notes = "添加或者更新apiConfig数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "appKey", value = "appKey", dataType = "String", required = true), @ApiImplicitParam(name = "appSecret", value = "appSecret", dataType = "String", required = true), @ApiImplicitParam(name = "companyId", value = "companyId", dataType = "Integer", required = false), @ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true), @ApiImplicitParam(name = "companyName", value = "companyName", dataType = "String", required = true)})
    public ResultBean addApiConfig(@RequestBody ApiConfig apiConfig) {
        logger.info("开始添加或者插入新的数据");
        return apiConfigService.add(apiConfig);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam()})
    @ApiOperation(value = "获取所有apiConfig数据", notes = "获取所有apiConfig数据")
    public ResultBean findAll() {
        logger.info("获取所有的数据");
        return apiConfigService.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除apiConfig数据", notes = "根据id删除apiConfig数据")
    @ApiImplicitParam(name = "id", value = "数据主键id", dataType = "String", required = true)
    public ResultBean deleteById(@PathVariable String id) {
        logger.info("开始删除数据：" + id);
        return apiConfigService.deleteById(id);
    }
}
