package com.crazy.finance.service;

import com.crazy.finance.bean.FlowOfFunds;

import java.util.List;

public interface FlowOfFundsService {
    List<FlowOfFunds> selectAllFlowOfFunds();

    List<FlowOfFunds> selectAllFlowOfFundsByUserId(Integer id);
}
