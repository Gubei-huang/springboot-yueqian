package com.crazy.finance.service;

import com.crazy.finance.bean.BankCard;

import java.math.BigDecimal;
import java.util.List;

public interface BankCardService  {
    List<BankCard> selectAllBankCardByUserId(Integer userId);

    void updateBankCardDefault(Integer id,Integer userId);

    BankCard selectDefaultBankCardByUserId(Integer userId);

    void insertBankCard(BankCard bankCard);

    BankCard selectBankCardById(Integer id);

    void updateBankCard(BankCard bankcard);

    void deleteBankCardById(Integer id);
    List<BankCard> selectAllBankCard();
    void updateBankCardBalance(Integer bankCardId, BigDecimal balance);
}
