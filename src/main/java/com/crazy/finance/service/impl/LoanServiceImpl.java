package com.crazy.finance.service.impl;

import com.crazy.finance.bean.BankCard;
import com.crazy.finance.bean.FlowOfFunds;
import com.crazy.finance.bean.Loan;
import com.crazy.finance.dao.BankCardDao;
import com.crazy.finance.dao.BankDao;
import com.crazy.finance.dao.FlowOfFundsDao;
import com.crazy.finance.dao.LoanDao;
import com.crazy.finance.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired(required = false)
    private LoanDao loanDao;
    @Autowired(required = false)
    private BankCardDao bankCardDao;
    @Autowired(required = false)
    private FlowOfFundsDao flowOfFundsDao;

    @Transactional
    @Override
    public void insertLoan(Loan loan) {
        loanDao.insertLoan(loan);
    }

    @Override
    public List<Loan> selectLoanByUserId(Integer id) {
        return loanDao.selectLoanByUserId(id);
    }

    @Transactional
    @Override
    public void updateLoanById(Integer id, Integer userId) throws Exception {
        BankCard bankCard = bankCardDao.selectDefaultBankCardByUserId(userId);
        BigDecimal balance = bankCard.getBalance();
        if (balance.compareTo(BigDecimal.ZERO) == 1) {
            List<Loan> loans = selectLoanByUserId(userId);
            for (Loan loan : loans) {
                if (loan.getId() == id) {
                    BigDecimal amount = loan.getAmount();
                    if (balance.compareTo(amount) != -1) {
                        BigDecimal multiply = balance.subtract(amount);
                        bankCardDao.updateBankCardBalance(bankCard.getId(), multiply);
                        loanDao.updateLoanById(id);
                        //资金记录
                        FlowOfFunds flowOfFunds = new FlowOfFunds(userId, amount, 1, "借贷还款", new Date(), "无");
                        flowOfFundsDao.insertFlowOfFundsDao(flowOfFunds);
                    } else {
                        throw new Exception();
                    }

                }
            }
        }
    }

    @Override
    public List<Loan> selectAllExamedLoan() {
        return loanDao.selectAllExamedLoan();
    }

    @Override
    public List<Loan> selectAllLoanByApplyStatusAsc() {
        return loanDao.selectAllLoanByApplyStatusAsc();
    }

    @Override
    public Loan selectLoanById(Integer id) {
        return loanDao.selectLoanById(id);
    }

    @Transactional
    @Override
    public void updateLoan(Loan loan) {

        loanDao.updateLoan(loan);
    }
}
