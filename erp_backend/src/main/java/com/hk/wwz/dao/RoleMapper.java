package com.hk.wwz.dao;

import com.hk.wwz.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    Role getRoleById(String id);

    List<Role> getAllRole();

    int insertRole(Role role);

    int updateRole(Role role);

    int deleteRole(String id);
}
