package com.crazy.finance.service;

import com.crazy.finance.bean.TermFinancial;
import com.crazy.finance.bean.UserTermFinancial;

import java.util.List;

public interface UserTermFinancialService {
    void insertuserTermFinancial(UserTermFinancial userTermFinancial);

    void buyTermFinancial(Integer userId, TermFinancial termFinancial) throws Exception;

    List<UserTermFinancial> selectAllUserTermFinancialById(Integer userId);


}
