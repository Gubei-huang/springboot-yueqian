package com.crazy.finance.service.impl;

import com.crazy.finance.bean.FlowOfFunds;
import com.crazy.finance.dao.FlowOfFundsDao;
import com.crazy.finance.service.FlowOfFundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowOfFundServiceImpl implements FlowOfFundsService {
    @Autowired(required = false)
    private FlowOfFundsDao flowOfFundsDao;

    @Override
    public List<FlowOfFunds> selectAllFlowOfFunds() {
        return flowOfFundsDao.selectAllFlowOfFunds();
    }

    @Override
    public List<FlowOfFunds> selectAllFlowOfFundsByUserId(Integer id) {
        return flowOfFundsDao.selectAllFlowOfFundsByUserId(id);
    }
}
