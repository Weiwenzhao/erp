package com.hk.wwz.controller;

import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.RoleUser;
import com.hk.wwz.service.RoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hk/wwz/roleUser")

@RestController
@CrossOrigin
@Api(value = "角色用户绑定")
public class RoleUserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RoleUserService roleUserService;

    @RequestMapping(value = "/username/{userName}", method = RequestMethod.GET)
    @ApiOperation(value = "开始根据用户名获取角色信息", notes = "开始根据用户名获取角色信息")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String")
    public ResultBean getRoleByUser(@PathVariable String userName) {
        logger.info("开始根据用户名获取角色信息");
        return roleUserService.getRoleByUser(userName);
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "添加新的角色用户绑定", notes = "添加新的角色用户绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "role", value = "角色ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user", value = "用户名", required = true, dataType = "String")
    })
    public ResultBean addRoleUser(@RequestBody RoleUser roleUser) {
        logger.info("添加新的角色用户绑定");
        return roleUserService.addRoleUser(roleUser);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的角色用户绑定", notes = "获取所有的角色用户绑定")
    @ApiImplicitParam(name = "", value = "", required = false)
    public ResultBean findAll() {
        logger.info("获取所有的角色用户绑定");
        return roleUserService.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据用户角色Id删除绑定关系", notes = "根据用户角色Id删除绑定关系")
    @ApiImplicitParam(name = "id", value = "角色用户绑定Id", required = true)
    public ResultBean deleteById(@PathVariable String id) {
        logger.info("根据用户角色Id删除绑定关系");
        return roleUserService.deleteById(id);
    }

}
