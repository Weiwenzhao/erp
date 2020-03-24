package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.hk.wwz.dao.RoleUserMapper;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.RoleUser;
import com.hk.wwz.service.RoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class RoleUserServiceImpl implements RoleUserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RoleUserMapper roleUserDao;

    @Override
    public ResultBean getRoleByUser(String userName) {
        RoleUser roleUser = roleUserDao.getRoleByUser(userName);
        logger.info("获取到的用户角色信息是：" + JSON.toJSONString(roleUser));
        return new ResultBean(200, JSON.toJSONString(roleUser));
    }

    @Override
    public ResultBean addRoleUser(RoleUser roleUser) {
        RoleUser roleUserData = roleUserDao.getRoleById(roleUser.getId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (roleUserData != null) {
            roleUser.setUpdateTime(timestamp);
            return new ResultBean(200, roleUserDao.updateRoleUser(roleUser));
        } else {
            roleUser.setId(UUID.randomUUID().toString().substring(0, 31));
            roleUser.setCreateTime(timestamp);
            return new ResultBean(200, roleUserDao.insertRoleUser(roleUser));
        }
    }

    @Override
    public ResultBean findAll() {
        List<RoleUser> roleUserList = roleUserDao.getAllRoleUser();
        logger.info("获取到的用户角色信息是：" + JSON.toJSONString(roleUserList));
        return new ResultBean(200, JSON.toJSONString(roleUserList));
    }

    @Override
    public ResultBean deleteById(String id) {
        return new ResultBean(200,roleUserDao.deleteRoleUser(id));
    }
}
