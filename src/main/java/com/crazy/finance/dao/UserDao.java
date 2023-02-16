package com.crazy.finance.dao;

import com.crazy.finance.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    User selectUserByUserPwd(@Param("username")String username,@Param("password")String password);

    User selectUserByUserName(String userName);

    void updateUserStatus(@Param("user") User user);

    List<User> selectAllUser();

    void insertUser(@Param("user")User user);

    User selectUserById(Integer id);

    void updateUserByUserId(@Param("user")User user);

    void updateUserPwdByUserId(Integer userId, String newPwd);

    void updateUserByAdmin(@Param("user")User user);

    Integer deleteUserById(Integer id);

    void insertUserByAdmin(@Param("user")User user);

    void updateUserReputation(@Param("user")User user);

    List<User> selectAllUserOnline();

    List<User> findUserByUsername(String username);

}
