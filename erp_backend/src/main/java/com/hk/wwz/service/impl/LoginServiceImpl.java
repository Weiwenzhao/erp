package com.hk.wwz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hk.wwz.dao.LoginMapper;
import com.hk.wwz.pojo.Login;
import com.hk.wwz.pojo.ResponObj;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    LoginMapper loginDao;

    @Override
    public ResponObj<Map<String,String>> login(Login login) {
        ResponObj<Map<String,String>> result = new ResponObj<>(-1,"login failed");
        try{
            QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name",login.getUserName());
            Login userInfo = loginDao.selectOne(queryWrapper);
            if(null != userInfo){
                result.setCode(0);
                result.setMsg("login success");
                Map<String,String> map  = new HashMap<>();
                map.put("token","admin-token");
                result.setData(map);
                return result;
            }
        }catch (Exception e){
            log.error("登陆失败！");
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultBean update(Login login) {
        if (login == null || (login != null && login.getUserName() == null)) {
            return new ResultBean(500, "用户名输入错误");
        }
        return new ResultBean(200, loginDao.update(login));
    }
}
