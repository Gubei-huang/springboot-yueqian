package com.crazy.finance.service;

import com.crazy.finance.bean.Admin;


public interface AdminService {

    Admin selectAdminByUserName(String adminName);

    Admin selectUserByUserPassword(String adminName,String password);

    void updateAdminStatus(Admin admin);
}
