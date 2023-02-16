package com.crazy.finance.service.impl;

import com.crazy.finance.bean.BankCard;
import com.crazy.finance.bean.FlowOfFunds;
import com.crazy.finance.bean.FundProduct;
import com.crazy.finance.bean.UserFundProduct;
import com.crazy.finance.dao.BankCardDao;
import com.crazy.finance.dao.FlowOfFundsDao;
import com.crazy.finance.dao.FundProductDao;
import com.crazy.finance.dao.UserFundProductDao;
import com.crazy.finance.service.UserFundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserFundProductServiceImpl implements UserFundProductService {
    @Autowired(required = false)
    private UserFundProductDao userFundProductDao;

    @Autowired(required = false)
    private BankCardDao bankCardDao;

    @Autowired(required = false)
    private FlowOfFundsDao flowOfFundsDao;


    /**
     * 买入基金理财
     * @param userId
     * @param fundProduct
     */
    @Transactional
    @Override
    public void buyFundProduct(Integer userId, FundProduct fundProduct) throws Exception{

        //获取投资时间
        String investerm = fundProduct.getInvesterm();
        String ends = investerm.substring(0, investerm.length() - 1);
        Integer integer = Integer.valueOf(ends);
        //获取结束时间
        Calendar calendar = Calendar.getInstance();
        Date endt = new Date();
        calendar.setTime(endt);
        calendar.add(Calendar.DATE,integer);
        Date endTime = calendar.getTime();

        //操作银行卡
        BigDecimal leastmoney = fundProduct.getLeastmoney();//金额
        BankCard bankCard = bankCardDao.selectDefaultBankCardByUserId(userId);//用户默认支付的银行卡
        BigDecimal money = bankCard.getBalance().subtract(leastmoney);
        if(money.compareTo(BigDecimal.ZERO)==-1)
            throw  new Exception();
        bankCardDao.updateBankCardBalance(bankCard.getId(), money);


        UserFundProduct userFundProduct = new UserFundProduct(userId, fundProduct.getId(), new Date(), endTime,1);
        insertUserFundProduct(userFundProduct);

        //资金记录
        FlowOfFunds flowOfFunds = new FlowOfFunds(userId, leastmoney, 1, fundProduct.getFunddesc(), new Date(), "无");
        flowOfFundsDao.insertFlowOfFundsDao(flowOfFunds);
    }

    @Transactional
    @Override
    public void insertUserFundProduct(UserFundProduct userFundProduct) {
        userFundProductDao.insertUserFundProduct(userFundProduct);
    }

    @Override
    public List<UserFundProduct> selectAllUserFundProductById(Integer userId) {
        return userFundProductDao.selectAllUserFundProductById(userId);
    }


}
