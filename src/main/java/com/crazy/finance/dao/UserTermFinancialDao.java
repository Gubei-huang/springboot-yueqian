package com.crazy.finance.dao;

import com.crazy.finance.bean.UserChangeMoney;
import com.crazy.finance.bean.UserFundProduct;
import com.crazy.finance.bean.UserTermFinancial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTermFinancialDao {
    void insertuserTermFinancial(@Param("userTermFinancial") UserTermFinancial userTermFinancial);

    List<UserTermFinancial> selectAllUserTermFinancialById(Integer userId);


}
