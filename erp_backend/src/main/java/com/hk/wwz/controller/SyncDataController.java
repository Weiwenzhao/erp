package com.hk.wwz.controller;

import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.SyncDataService;
import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hk/wwz/sync")

@RestController
@CrossOrigin
@Api(value = "从钉钉获取同步信息")
public class SyncDataController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    SyncDataService syncDataService;

    @RequestMapping(value = "/depart", method = RequestMethod.GET)
    @ApiOperation(value = "同步部门信息接口", notes = "同步部门信息")
    @ApiImplicitParam(name = "", value = "", required = false, dataType = "String")
    public ResultBean syncDepart() throws ApiException {
        logger.info("开始同步部门信息");
        return syncDataService.syncDepartData();
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ApiOperation(value = "同步用户信息表",notes = "同步用户信息表")
    @ApiImplicitParam()
    public ResultBean syncUser()throws ApiException{
        logger.info("开始同步用户信息");
        return syncDataService.syncUser();
    }
}
