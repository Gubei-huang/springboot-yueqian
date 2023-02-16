package com.crazy.finance.dao;

import com.crazy.finance.bean.Permissions;

import java.util.List;

public interface PermissionDao{

    List<Permissions> selectUserById(Integer userid);

    List<Permissions> selectAdminById(Integer adminid);

    Permissions selectPermissionsById(Integer id);

    Permissions selectPermissionsByPermission(String permissionName);
}
