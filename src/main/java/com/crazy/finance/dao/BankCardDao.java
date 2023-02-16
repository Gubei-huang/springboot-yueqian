package com.crazy.finance.dao;

import com.crazy.finance.bean.BankCard;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BankCardDao {

 List<BankCard> selectAllBankCardByUserId(Integer userId);

    void updateBankCardDufault(Integer userId );

    void updateBankCardByBankCardId(@Param("id") Integer id,@Param("userId") Integer userId);

    BankCard selectDefaultBankCardByUserId(Integer userId);

    void updateBankCardBalance(Integer bankCardId,BigDecimal balance);

    void insertBankCard(@Param("bankCard") BankCard bankCard);

   BankCard selectBankCardById(Integer id);

    void updateBankCard(@Param("bankCard") BankCard bankCard);

    void deleteBankCardById(Integer id);

    List<BankCard> selectAllBankCard();
}
