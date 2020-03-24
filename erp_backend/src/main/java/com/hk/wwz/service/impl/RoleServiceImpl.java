package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.hk.wwz.dao.RoleMapper;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.Role;
import com.hk.wwz.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RoleMapper roleDao;

    @Override
    public ResultBean addRole(Role role) {
        Role roleData = roleDao.getRoleById(role.getId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (roleData != null) {
            role.setUpdateTime(timestamp);
            return new ResultBean(200, roleDao.updateRole(role));
        } else {
            role.setId(UUID.randomUUID().toString().substring(0, 31));
            role.setCreateTime(timestamp);
            return new ResultBean(200, roleDao.insertRole(role));
        }
    }

    @Override
    public ResultBean findAll() {
        List<Role> roleUserList = roleDao.getAllRole();
        logger.info("获取到的用户角色信息是：" + JSON.toJSONString(roleUserList));
        return new ResultBean(200, JSON.toJSONString(roleUserList));
    }

    @Override
    public ResultBean deleteById(String id) {
        return new ResultBean(200,roleDao.deleteRole(id));
    }
}
