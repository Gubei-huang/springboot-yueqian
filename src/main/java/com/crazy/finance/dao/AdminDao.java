package com.crazy.finance.dao;

import com.crazy.finance.bean.Admin;
import com.crazy.finance.bean.User;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {

    Admin selectAdminByAdminName(String adminName);

    void updateAdminStatus(@Param("admin") Admin admin);

    Admin selectAdminById(Integer id);
}
