package com.hk.wwz.service;

import com.hk.wwz.dto.RoleDto;
import com.hk.wwz.pojo.ResponObj;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.Role;

import java.util.List;

public interface RoleService {
    ResponObj<Integer> addRole(Role role);

    List<Role> findAllByCompany(RoleDto roleDto);

    ResultBean deleteById(String id);
}
