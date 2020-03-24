package com.hk.wwz.controller;

import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.DepartService;
import com.hk.wwz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hk/wwz/user")

@RestController
@CrossOrigin
@Api(value = "用户管理")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UserService userService;


    @ApiOperation(value = "用户分页查询接口", notes = "用户分页查询接口")
    @RequestMapping(value = "/page/{beginIndex}/{endIndex}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginIndex", value = "开始位置，大于1", paramType = "Integer", required = true),
            @ApiImplicitParam(name = "endIndex", value = "结束位置，大于1", paramType = "Integer", required = true)
    })
    public ResultBean getPageDepart(@PathVariable int beginIndex, @PathVariable int endIndex) {
        logger.info("开始分页查询人员信息");
        return userService.getUserInfo(beginIndex, endIndex);
    }
}
