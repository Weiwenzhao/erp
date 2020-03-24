package com.hk.wwz.service.impl;

import com.hk.wwz.dao.LoginMapper;
import com.hk.wwz.pojo.Login;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LoginMapper loginDao;

    @Override
    public ResultBean login(Login login) {
        if (login == null || (login != null && login.getUserName() == null)) {
            return new ResultBean(500, "用户名输入错误");
        }
        Login loginData = loginDao.login(login.getUserName());
        if (loginData == null) {
            return new ResultBean(500, "用户名密码输入错误");
        } else {
            if (!loginData.getPassword().equals(login.getPassword())) {
                return new ResultBean(301, "密码输入错误");
            } else {
                return new ResultBean(200, "success");
            }
        }
    }

    @Override
    public ResultBean update(Login login) {
        if (login == null || (login != null && login.getUserName() == null)) {
            return new ResultBean(500, "用户名输入错误");
        }
        return new ResultBean(200, loginDao.update(login));
    }
}
