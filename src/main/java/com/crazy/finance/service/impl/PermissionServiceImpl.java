package com.crazy.finance.service.impl;

import com.crazy.finance.bean.Permissions;
import com.crazy.finance.dao.PermissionDao;
import com.crazy.finance.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired(required = false)
    private PermissionDao permissionDao;
    @Override
    public List<Permissions> selectUserById(Integer userid) {

        return permissionDao.selectUserById(userid);
    }

    @Override
    public List<Permissions> selectAdminById(Integer adminid) {

        return permissionDao.selectAdminById(adminid);
    }

    @Override
    public Permissions selectPermissionsByPermission(String permissionName) {

        return permissionDao.selectPermissionsByPermission(permissionName);
    }
}
