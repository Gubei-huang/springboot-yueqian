package com.crazy.finance.service;

import com.crazy.finance.bean.Permissions;

import javax.net.ssl.SSLSession;
import java.util.List;
import java.util.Map;

public interface PermissionService {

    List<Permissions> selectUserById(Integer userid);

    List<Permissions> selectAdminById(Integer adminid);


    Permissions selectPermissionsByPermission(String permissionName);
}
