package com.crazy.finance.dao;

import com.crazy.finance.bean.UserChangeMoney;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface UserChangeMoneyDao {

    void insertUserChangeMoney(@Param("userChangeMoney") UserChangeMoney userChangeMoney);

    List<UserChangeMoney> selectAllUserChangeMoneyById(Integer userId);


    void updateUserChange(@Param("userChangeMoney") UserChangeMoney userChangeMoney);

    UserChangeMoney selectUserChangeMoneyById(Integer id);

    void  deleteUserChangeMoneyById(Integer id);
}
