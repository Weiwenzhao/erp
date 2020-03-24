package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.hk.wwz.dao.DepartMapper;
import com.hk.wwz.pojo.Depart;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.DepartService;
import com.hk.wwz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DepartMapper departDao;
    @Autowired
    UserService userService;

    @Override
    public ResultBean getDepartInfo(int begin, int end) {
        List<Depart> allDepartList = departDao.getAllDepart();
        logger.info("获取到的所有部门信息是：" + JSON.toJSONString(allDepartList));
        List<Depart> pageDepart = userService.pageSize(begin, end, allDepartList);
        logger.info("获取到的分页部门信息是：" + JSON.toJSONString(pageDepart));
        return new ResultBean(200, JSON.toJSONString(pageDepart));
    }
}
