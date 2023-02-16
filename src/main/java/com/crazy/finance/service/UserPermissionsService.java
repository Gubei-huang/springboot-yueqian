package com.crazy.finance.service;

import com.crazy.finance.bean.UserPermissions;

import java.util.List;

public interface UserPermissionsService {
    List<UserPermissions> selectUserPermissionsByUserId(Integer id);

    void deleteAllUserPermissionsByUserId(Integer id);

    void insertUserPermissions(UserPermissions up);
}
