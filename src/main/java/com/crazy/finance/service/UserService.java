package com.crazy.finance.service;

import com.crazy.finance.bean.User;

import java.util.List;

public interface UserService {

  User selectUserByUserName(String userName);

  User selectUserByUserPassword(String userName,String password);

  void updateUserStatus(User user);

  List<User> selectAllUser(Integer pageNum,Integer pageSize);

    void insertUser(User user);

  User selectUserById(Integer id);

  void updateUserByUserId(User user);

    void updateUserPwdByUserId(Integer userId, String newPwd);

  void updateUserByAdmin(User user);

  Integer deleteUserById(Integer id);

  void insertUserByAdmin(User user);

  void updateUserReputation(User user);

    List<User> selectAllUserOnline(Integer pageNum, Integer pageSize);

    List<User> findUserByUsername(String username);
}
