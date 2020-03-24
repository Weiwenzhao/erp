package com.hk.wwz.controller;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.hk.wwz.pojo.ApiConfig;
import com.hk.wwz.pojo.Login;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.LoginService;
import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/hk/wwz")

@RestController
@CrossOrigin
@Api(value = "登陆鉴权表")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆鉴权接口", notes = "登陆鉴权接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "登录用户名", dataType = "String", required = true), @ApiImplicitParam(name = "password", value = "登录密码", dataType = "String", required = true), @ApiImplicitParam(name = "isLocked", value = "用户锁定", dataType = "Integer", required = false), @ApiImplicitParam(name = "id", value = "id", required = false)})
    public ResultBean login(@RequestBody Login login) {
        logger.info("开始登陆");
        return loginService.login(login);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码接口", notes = "修改密码接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "登录用户名", dataType = "String", required = true), @ApiImplicitParam(name = "password", value = "登录密码", dataType = "String", required = true), @ApiImplicitParam(name = "isLocked", value = "用户锁定", dataType = "Integer", required = false), @ApiImplicitParam(name = "id", value = "id", dataType = "Integer", required = false)})
    public ResultBean update(@RequestBody Login login) {
        logger.info("修改密码");
        return loginService.update(login);
    }
}
