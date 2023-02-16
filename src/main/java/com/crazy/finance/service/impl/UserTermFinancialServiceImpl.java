package com.crazy.finance.service.impl;

import com.crazy.finance.bean.BankCard;
import com.crazy.finance.bean.FlowOfFunds;
import com.crazy.finance.bean.TermFinancial;
import com.crazy.finance.bean.UserTermFinancial;
import com.crazy.finance.dao.BankCardDao;
import com.crazy.finance.dao.FlowOfFundsDao;
import com.crazy.finance.dao.UserTermFinancialDao;
import com.crazy.finance.service.UserTermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserTermFinancialServiceImpl implements UserTermFinancialService {
    @Autowired(required = false)
    private UserTermFinancialDao userTermFinancialDao;
    @Autowired(required = false)
    private BankCardDao bankCardDao;

    @Autowired(required = false)
    private FlowOfFundsDao flowOfFundsDao;

    @Transactional
    @Override
    public void insertuserTermFinancial(UserTermFinancial userTermFinancial) {
        userTermFinancialDao.insertuserTermFinancial(userTermFinancial);
    }

    @Transactional
    @Override
    public synchronized void buyTermFinancial(Integer userId, TermFinancial termFinancial) throws Exception{

        //获取投资时间
        String investerm = termFinancial.getInvesterm();
        String ends = investerm.substring(0, investerm.length() - 1);
        Integer integer = Integer.valueOf(ends);
        //获取结束时间
        Calendar calendar = Calendar.getInstance();
        Date endt = new Date();
        calendar.setTime(endt);
        calendar.add(Calendar.DATE,integer);
        Date endTime = calendar.getTime();


        BigDecimal leastmoney = termFinancial.getLeastmoney();//投资金额
        BankCard bankCard = bankCardDao.selectDefaultBankCardByUserId(userId);//用户默认支付的银行卡
        BigDecimal money = bankCard.getBalance().subtract(leastmoney);
        if(money.compareTo(BigDecimal.ZERO)==-1)
            throw  new Exception();
        bankCardDao.updateBankCardBalance(bankCard.getId(), money);

        UserTermFinancial userTermFinancial = new UserTermFinancial(userId, termFinancial.getId(), new Date(),endTime,  1);
        insertuserTermFinancial(userTermFinancial);

        //资金记录
        FlowOfFunds flowOfFunds = new FlowOfFunds(userId, leastmoney, 1, termFinancial.getName(), new Date(), "无");
        flowOfFundsDao.insertFlowOfFundsDao(flowOfFunds);
    }

    @Override
    public List<UserTermFinancial> selectAllUserTermFinancialById(Integer userId) {
        return userTermFinancialDao.selectAllUserTermFinancialById(userId);
    }


}
