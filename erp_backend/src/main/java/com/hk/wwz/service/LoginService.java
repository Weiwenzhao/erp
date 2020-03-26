package com.hk.wwz.service;

import com.hk.wwz.pojo.Login;
import com.hk.wwz.pojo.ResponObj;
import com.hk.wwz.pojo.ResultBean;

public interface LoginService {
    ResponObj<String> login(Login login);

    ResultBean update(Login login);
}
