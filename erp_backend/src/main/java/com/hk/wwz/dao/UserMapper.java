package com.hk.wwz.dao;

import com.hk.wwz.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getAllUser();

    User getUser(String userId);

    int deleteUser(List<String> deleteUserList);

    int addUser(User depart);

    int addUserList(List<User> userList);

}
