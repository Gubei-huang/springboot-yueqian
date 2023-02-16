package com.crazy.finance.dao;

import com.crazy.finance.bean.Loan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanDao {
    void insertLoan(@Param("loan") Loan loan);

    List<Loan> selectLoanByUserId(Integer id);

    void updateLoanById(Integer id);

    List<Loan> selectAllExamedLoan();

    List<Loan> selectAllLoanByApplyStatusAsc();

    Loan selectLoanById(Integer id);

    void updateLoan(@Param("loan") Loan loan);
}
