package com.crazy.finance.service.impl;

import com.crazy.finance.bean.UserPermissions;
import com.crazy.finance.dao.UserPermissionsDao;
import com.crazy.finance.service.UserPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPermissionsServiceImpl implements UserPermissionsService {

    @Autowired(required = false)
    private UserPermissionsDao userPermissionsDao;
    @Override
    public List<UserPermissions> selectUserPermissionsByUserId(Integer id) {
        return userPermissionsDao.selectUserPermissionsByUserId(id);
    }

    @Override
    public void deleteAllUserPermissionsByUserId(Integer id) {
        userPermissionsDao.deleteAllUserPermissionsByUserId(id);
    }

    @Override
    public void insertUserPermissions(UserPermissions up) {
           userPermissionsDao.insertUserPermissions(up);
    }
}
