package com.hk.wwz.service;

import com.hk.wwz.pojo.Login;
import com.hk.wwz.pojo.ResponObj;
import com.hk.wwz.pojo.ResultBean;

import java.util.Map;

public interface LoginService {
    ResponObj<Map<String,String>> login(Login login);

    ResultBean update(Login login);
}
