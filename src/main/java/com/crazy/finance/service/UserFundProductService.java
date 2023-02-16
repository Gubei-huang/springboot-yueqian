package com.crazy.finance.service;

import com.crazy.finance.bean.FundProduct;
import com.crazy.finance.bean.UserFundProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFundProductService {
    void buyFundProduct(Integer userId, FundProduct fundProduct) throws Exception;

    void insertUserFundProduct( UserFundProduct userFundProduct);

    List<UserFundProduct> selectAllUserFundProductById(Integer userId);


}
