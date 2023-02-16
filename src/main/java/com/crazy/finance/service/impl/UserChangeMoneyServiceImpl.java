package com.crazy.finance.service.impl;

import com.crazy.finance.bean.*;
import com.crazy.finance.dao.BankCardDao;
import com.crazy.finance.dao.FlowOfFundsDao;
import com.crazy.finance.dao.UserChangeMoneyDao;
import com.crazy.finance.service.UserChangeMoneyService;
import com.crazy.finance.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserChangeMoneyServiceImpl implements UserChangeMoneyService {
    @Autowired(required = false)
    private UserChangeMoneyDao userChangeMoneyDao;

    @Autowired(required = false)
    private BankCardDao bankCardDao;

    @Autowired(required = false)
    private FlowOfFundsDao flowOfFundsDao;

    /**
     * 用户——零钱理财数据信息
     *
     * @param userChangeMoney 信息对象
     */
    @Transactional
    @Override
    public void insertUserChangeMoney(UserChangeMoney userChangeMoney) {

        userChangeMoneyDao.insertUserChangeMoney(userChangeMoney);
    }

    /**
     * 买零钱理财
     *
     * @param userId      用户id
     * @param changeMoney 零钱理财对象
     */
    @Transactional
    @Override
    public synchronized void buyChangeMoney(Integer userId, ChangeMoney changeMoney) throws Exception{
        BigDecimal invesmoney = changeMoney.getInvesmoney();//投资金额

        BankCard bankCard = bankCardDao.selectDefaultBankCardByUserId(userId);//用户默认支付的银行卡

        BigDecimal money = bankCard.getBalance().subtract(invesmoney);
          if(money.compareTo(BigDecimal.ZERO)==-1)
            throw  new Exception();
        bankCardDao.updateBankCardBalance(bankCard.getId(), money);

        UserChangeMoney userChangeMoney = new UserChangeMoney(userId, changeMoney.getId(), new Date(), new Date(), 1);
        insertUserChangeMoney(userChangeMoney);

        //资金记录
        FlowOfFunds flowOfFunds = new FlowOfFunds(userId, invesmoney, 1, changeMoney.getName(), new Date(), "无");
        flowOfFundsDao.insertFlowOfFundsDao(flowOfFunds);
    }

    /**
     * 查询用户的零钱理财（所有的）
     *
     * @param userId
     * @return
     */
    @Transactional
    @Override
    public List<UserChangeMoney> selectAllUserChangeMoneyById(Integer userId) {
        List<UserChangeMoney> list = userChangeMoneyDao.selectAllUserChangeMoneyById(userId);
        for (UserChangeMoney userChangeMoney : list) {

            Date starttime = userChangeMoney.getStarttime();//投资开始时间
            Date date = new Date();
            BigDecimal day = DateUtils.subtract(starttime, date);//开始和结束日期差了几天

            BigDecimal peiincome = userChangeMoney.getChangeMoney().getPeiincome();//日利率
            BigDecimal multiply = peiincome.multiply(day);//收益
            BigDecimal profit = userChangeMoney.getProfit();//上一次的收益
            userChangeMoney.setProfit(multiply);
            userChangeMoney.setAlsttime(new Date());
            userChangeMoneyDao.updateUserChange(userChangeMoney);
            if (multiply.compareTo(BigDecimal.ZERO)==1) {
                BankCard bankCard = bankCardDao.selectDefaultBankCardByUserId(userId);//用户默认支付的银行卡
                BigDecimal money = bankCard.getBalance().add(multiply.subtract(profit));
                bankCardDao.updateBankCardBalance(bankCard.getId(), money);
            }
        }

        return list;
    }

    /**
     * 删除用户的零钱理财
     *
     * @param userChangeMoneyId
     * @param userId
     */
    @Transactional
    @Override
    public void deleteUserChangeMoneyById(Integer userChangeMoneyId, Integer userId) {
        UserChangeMoney userChangeMoney = selectUserChangeMoneyById(userChangeMoneyId);

        Date starttime = userChangeMoney.getStarttime();//投资开始时间
        BigDecimal day = DateUtils.subtract(starttime, new Date());//开始和结束日期差了几天
        BigDecimal peiincome = userChangeMoney.getChangeMoney().getPeiincome();//日利率
        BigDecimal multiply = peiincome.multiply(day);//收益
        BigDecimal profit = userChangeMoney.getProfit();//上一次的收益
        userChangeMoney.setProfit(multiply);
        userChangeMoney.setAlsttime(new Date());
        userChangeMoneyDao.updateUserChange(userChangeMoney);

        if (multiply.compareTo(BigDecimal.ZERO)==1) {
            BankCard bankCard = bankCardDao.selectDefaultBankCardByUserId(userId);//用户默认支付的银行卡
            BigDecimal money = bankCard.getBalance().add(multiply.subtract(profit));
            bankCardDao.updateBankCardBalance(bankCard.getId(), money);
        }
        userChangeMoneyDao.deleteUserChangeMoneyById(userChangeMoneyId);
    }

    @Override
    public UserChangeMoney selectUserChangeMoneyById(Integer id) {
        return userChangeMoneyDao.selectUserChangeMoneyById(id);
    }

}
