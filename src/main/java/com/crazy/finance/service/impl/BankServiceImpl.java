package com.crazy.finance.service.impl;

import com.crazy.finance.bean.Bank;
import com.crazy.finance.dao.BankDao;
import com.crazy.finance.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    @Autowired(required = false)
    private BankDao bankDao;

    @Override
    public List<Bank> selectAllBank() {
        return bankDao.selectAllBank();
    }

    @Transactional
    @Override
    public void insertBank(Bank bank) {
         bankDao.insertBank(bank);
    }

    @Override
    public Bank selectBankById(Integer id) {
        return bankDao.selectBankById(id);
    }

    @Transactional
    @Override
    public void updateBank(Bank bank) {
        bankDao.updateBank(bank);
    }

    @Transactional
    @Override
    public void deleteBankById(Integer id) {
        bankDao.deleteBankById(id);
    }
}
