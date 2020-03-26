package com.hk.wwz.service;

import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.RoleUser;

public interface RoleUserService {
    ResultBean getRoleByUser(String userName);

    ResultBean addRoleUser(RoleUser roleUser);

    ResultBean findAll();

    ResultBean deleteById(String id);
}
