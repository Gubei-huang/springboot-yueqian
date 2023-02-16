package com.crazy.finance.service;

import com.crazy.finance.bean.Bank;

import java.util.List;

public interface BankService {
    List<Bank> selectAllBank();

    void insertBank(Bank bank);

    Bank selectBankById(Integer id);

    void updateBank(Bank bank);

    void deleteBankById(Integer id);
}
