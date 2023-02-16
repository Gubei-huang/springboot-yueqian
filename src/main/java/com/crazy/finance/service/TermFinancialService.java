package com.crazy.finance.service;

import com.crazy.finance.bean.TermFinancial;

import java.util.List;

public interface TermFinancialService {
    List<TermFinancial> selectAllTermFinancial();
    TermFinancial selectTermFinancialById(Integer termFinancialId);

    void insertTermFinancial(TermFinancial termFinancial);

    void updateTermFinancial(TermFinancial termFinancial);

    void deleteTermFinancialById(Integer id);
}
