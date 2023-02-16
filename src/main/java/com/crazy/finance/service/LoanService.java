package com.crazy.finance.service;

import com.crazy.finance.bean.Loan;

import java.util.List;

public interface LoanService {
    void insertLoan(Loan loan);

    List<Loan> selectLoanByUserId(Integer id);

    void updateLoanById(Integer id, Integer userId) throws Exception;

    List<Loan> selectAllExamedLoan();

    List<Loan> selectAllLoanByApplyStatusAsc();

    Loan selectLoanById(Integer id);

    void updateLoan(Loan loan);
}
