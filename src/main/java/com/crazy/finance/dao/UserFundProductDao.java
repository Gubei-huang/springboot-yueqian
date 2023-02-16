package com.crazy.finance.dao;

import com.crazy.finance.bean.FundProduct;
import com.crazy.finance.bean.UserChangeMoney;
import com.crazy.finance.bean.UserFundProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFundProductDao {
    void insertUserFundProduct(@Param("userFundProduct") UserFundProduct userFundProduct);

    List<UserFundProduct> selectAllUserFundProductById(Integer userId);


}
