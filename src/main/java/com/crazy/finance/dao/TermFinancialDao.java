package com.crazy.finance.dao;

import com.crazy.finance.bean.TermFinancial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermFinancialDao {
    List<TermFinancial> selectAllTermFinancial();

    TermFinancial selectTermFinancialById(Integer termFinancialId);

    void insertTermFinancial(@Param("termFinancial") TermFinancial termFinancial);

    void updateTermFinancial(@Param("termFinancial") TermFinancial termFinancial);

    void deleteTermFinancialById(Integer id);
}
