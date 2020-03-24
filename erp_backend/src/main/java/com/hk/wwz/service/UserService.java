package com.hk.wwz.service;

import com.hk.wwz.pojo.ResultBean;

import java.util.List;

public interface UserService {
    ResultBean getUserInfo(int begin, int end);

    List pageSize(int begin, int end, List list);
}
