package com.crazy.finance.service.impl;

import com.crazy.finance.bean.BankCard;
import com.crazy.finance.dao.BankCardDao;
import com.crazy.finance.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankCardServiceImpl implements BankCardService {
    @Autowired(required = false)
    private BankCardDao bankCardDao;

    @Override
    public List<BankCard> selectAllBankCardByUserId(Integer userId) {
        return bankCardDao.selectAllBankCardByUserId(userId);
    }

    @Transactional
    @Override
    public void updateBankCardDefault(Integer id, Integer userId) {
        bankCardDao.updateBankCardDufault(userId);
        bankCardDao.updateBankCardByBankCardId(id, userId);
    }

    @Override
    public BankCard selectDefaultBankCardByUserId(Integer userId) {
        return bankCardDao.selectDefaultBankCardByUserId(userId);
    }

    @Transactional
    @Override
    public void insertBankCard(BankCard bankCard) {
        bankCardDao.insertBankCard(bankCard);
    }

    @Override
    public BankCard selectBankCardById(Integer id) {
        return bankCardDao.selectBankCardById(id);
    }

    @Transactional
    @Override
    public void updateBankCard(BankCard bankcard) {
               bankCardDao.updateBankCard(bankcard);
    }

    @Override
    public void deleteBankCardById(Integer id) {
        bankCardDao.deleteBankCardById(id);
    }

    @Override
    public List<BankCard> selectAllBankCard() {
        return bankCardDao.selectAllBankCard();
    }

    @Override
    public void updateBankCardBalance(Integer bankCardId, BigDecimal balance) {
            bankCardDao.updateBankCardBalance(bankCardId,balance);
    }
}
