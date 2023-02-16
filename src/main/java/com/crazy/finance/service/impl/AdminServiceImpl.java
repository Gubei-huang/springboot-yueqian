package com.crazy.finance.service.impl;

import com.crazy.finance.bean.Admin;
import com.crazy.finance.bean.User;
import com.crazy.finance.dao.AdminDao;
import com.crazy.finance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private AdminDao adminDao;

    @Override
    public Admin selectAdminByUserName(String adminName) {
        return adminDao.selectAdminByAdminName(adminName);
    }

    @Override
    public Admin selectUserByUserPassword(String adminName, String password) {
        Admin admin = adminDao.selectAdminByAdminName(adminName);
            return admin;
    }
    @Transactional
    @Override
    public void updateAdminStatus(Admin admin) {
        adminDao.updateAdminStatus(admin);
    }
}
