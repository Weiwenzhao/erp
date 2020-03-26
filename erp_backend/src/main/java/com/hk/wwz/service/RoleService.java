package com.hk.wwz.service;

import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.Role;

public interface RoleService {
    ResultBean addRole(Role role);

    ResultBean findAll();

    ResultBean deleteById(String id);
}
