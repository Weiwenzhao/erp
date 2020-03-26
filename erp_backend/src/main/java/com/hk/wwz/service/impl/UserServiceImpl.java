package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.hk.wwz.dao.UserMapper;
import com.hk.wwz.pojo.Depart;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.User;
import com.hk.wwz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UserMapper userDao;

    @Override
    public ResultBean getUserInfo(int begin, int end) {
        List<User> allUserList = userDao.getAllUser();
        logger.info("获取到的所有部门信息是：" + JSON.toJSONString(allUserList));
        List<User> pageUserList = pageSize(begin, end, allUserList);
        logger.info("获取到的分页部门信息是：" + JSON.toJSONString(pageUserList));
        return new ResultBean(200, JSON.toJSONString(pageUserList));
    }

    @Override
    public List pageSize(int begin, int end, List allList) {
        List<User> resultList = new ArrayList<>();
        if (allList != null) {
            int length = allList.size();
            if (end >= length) {
                resultList = allList.subList(begin - 1, length);
            } else {
                resultList = allList.subList(begin - 1, end);
            }
        }
        return resultList;
    }
}
