package com.crazy.finance.service.impl;

import com.crazy.finance.bean.User;
import com.crazy.finance.dao.UserDao;
import com.crazy.finance.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public User selectUserByUserName(String userName) {
        return userDao.selectUserByUserName(userName);
    }

    @Override
    public User selectUserByUserPassword(String userName, String password) {
        User user = userDao.selectUserByUserPwd(userName, password);
        return user;
    }

    @Transactional
    @Override
    public void updateUserStatus(User user) {
        userDao.updateUserStatus(user);
    }

    @Override
    public List<User> selectAllUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userDao.selectAllUser();
    }

    @Transactional
    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }

    @Transactional
    @Override
    public void updateUserByUserId(User user) {
        userDao.updateUserByUserId(user);
    }

    @Transactional
    @Override
    public void updateUserPwdByUserId(Integer userId, String newPwd) {
        userDao.updateUserPwdByUserId(userId, newPwd);
    }

    @Transactional
    @Override
    public void updateUserByAdmin(User user) {
        userDao.updateUserByAdmin(user);
    }

    @Transactional
    @Override
    public Integer deleteUserById(Integer id) {
        return userDao.deleteUserById(id);
    }

    @Transactional
    @Override
    public void insertUserByAdmin(User user) {

        userDao.insertUserByAdmin(user);
    }

    @Transactional
    @Override
    public void updateUserReputation(User user) {
        userDao.updateUserReputation(user);
    }

    @Override
    public List<User> selectAllUserOnline(Integer pageNum, Integer pageSize) {
        return userDao.selectAllUserOnline();
    }

    @Override
    public List<User> findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
