package com.crazy.finance.service;

import com.crazy.finance.bean.AdminPermissions;

import java.util.List;

public interface AdminPermissionsService {
    List<AdminPermissions> selectAdminPermissionsByAdminId(Integer id);

    void updateAllAdminPermissions();

    void updateAdminPermissions(AdminPermissions ap);
}
