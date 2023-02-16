package com.crazy.finance.service;

import com.crazy.finance.bean.FundProduct;

import java.util.List;

public interface FundProductService {
    List<FundProduct> selectAllFundProduct();

    FundProduct selectFundProductById(Integer fundProductId);

    void insertFundProduct(FundProduct fundProduct);

    void updateFundProduct(FundProduct fundProduct);

    void deleteFundProductById(Integer id);
}
