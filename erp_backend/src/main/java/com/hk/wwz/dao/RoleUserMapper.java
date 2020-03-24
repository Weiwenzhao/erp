package com.hk.wwz.dao;

import com.hk.wwz.pojo.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserMapper {
    RoleUser getRoleByUser(String user);

    RoleUser getRoleById(String id);

    List<RoleUser> getAllRoleUser();

    int insertRoleUser(RoleUser roleUser);

    int updateRoleUser(RoleUser roleUser);

    int deleteRoleUser(String id);
}
