package com.hk.wwz.controller;

import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.Role;
import com.hk.wwz.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hk/wwz/role")

@RestController
@CrossOrigin
@Api(value = "角色用户绑定")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "添加新的角色用户绑定", notes = "添加新的角色用户绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "role", value = "角色ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "角色名", required = true, dataType = "String")
    })
    public ResultBean addRoleUser(@RequestBody Role role) {
        logger.info("添加新的角色用户绑定");
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的角色用户绑定", notes = "获取所有的角色用户绑定")
    @ApiImplicitParam(name = "", value = "", required = false)
    public ResultBean findAll() {
        logger.info("获取所有的角色用户绑定");
        return roleService.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据角色Id删除角色信息", notes = "根据角色Id删除角色信息")
    @ApiImplicitParam(name = "id", value = "角色用户绑定Id", required = true)
    public ResultBean deleteById(@PathVariable String id) {
        logger.info("根据角色Id删除角色信息");
        return roleService.deleteById(id);
    }

}
