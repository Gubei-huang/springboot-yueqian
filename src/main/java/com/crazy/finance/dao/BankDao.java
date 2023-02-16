package com.crazy.finance.dao;

import com.crazy.finance.bean.Bank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankDao {

    List<Bank> selectAllBank();

    void insertBank(@Param("bank") Bank bank);

    Bank selectBankById(Integer id);

    void updateBank(@Param("bank")Bank bank);

    void deleteBankById(Integer id);
}
