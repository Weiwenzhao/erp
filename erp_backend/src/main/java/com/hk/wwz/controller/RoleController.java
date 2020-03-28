package com.hk.wwz.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hk.wwz.dto.RoleDto;
import com.hk.wwz.pojo.ResponObj;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.Role;
import com.hk.wwz.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/role")

@RestController
@CrossOrigin
@Api(value = "角色用户绑定")
@Slf4j
public class RoleController {

    @Resource
    RoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加新的角色用户绑定", notes = "添加新的角色用户绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "role", value = "角色ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "角色名", required = true, dataType = "String")
    })
    public ResponObj<Integer> addRoleUser(@RequestBody Role role) {
        ResponObj<Integer> result = new ResponObj<>(-1,"get role info failed");
        if(null == role || StringUtils.isBlank(role.getName()) || StringUtils.isBlank(role.getCompanyId())){
            result.setMsg("不合法的参数");
            return result;
        }
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/findAllRole", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有的角色用户绑定", notes = "获取所有的角色用户绑定")
    @ApiImplicitParam(name = "", value = "", required = false)
    public ResponObj<List<Role>> findAll(@RequestBody RoleDto roleDto) {
        ResponObj<List<Role>> result = new ResponObj<>(-1,"get role info failed");
        if(null == roleDto){
            return result;
        }
        result.setCode(0);
        result.setMsg("success");
        result.setData(roleService.findAllByCompany(roleDto));
        return result;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据角色Id删除角色信息", notes = "根据角色Id删除角色信息")
    @ApiImplicitParam(name = "id", value = "角色用户绑定Id", required = true)
    public ResultBean deleteById(@PathVariable String id) {
        log.info("根据角色Id删除角色信息");
        return roleService.deleteById(id);
    }

}
