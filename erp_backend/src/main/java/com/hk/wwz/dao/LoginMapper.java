package com.hk.wwz.dao;

import com.hk.wwz.pojo.Login;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    Login login(String userName);

    int update(Login login);
}
