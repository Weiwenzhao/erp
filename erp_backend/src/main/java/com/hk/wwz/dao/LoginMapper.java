package com.hk.wwz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hk.wwz.pojo.Login;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper extends BaseMapper<Login> {
    Login login(String userName);

    int update(Login login);
}
