package com.crazy.finance.service.impl;

import com.crazy.finance.bean.TermFinancial;
import com.crazy.finance.dao.TermFinancialDao;
import com.crazy.finance.service.TermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermFinancialServiceImpl implements TermFinancialService {
    @Autowired(required = false)
    private TermFinancialDao termFinancialDao;

    @Override
    public List<TermFinancial> selectAllTermFinancial() {
        return termFinancialDao.selectAllTermFinancial();
    }

    @Override
    public TermFinancial selectTermFinancialById(Integer termFinancialId) {
        return termFinancialDao.selectTermFinancialById(termFinancialId);
    }

    @Transactional
    @Override
    public void insertTermFinancial(TermFinancial termFinancial) {
       termFinancialDao.insertTermFinancial(termFinancial);
    }

    @Transactional
    @Override
    public void updateTermFinancial(TermFinancial termFinancial) {
        termFinancialDao.updateTermFinancial(termFinancial);
    }

    @Transactional
    @Override
    public void deleteTermFinancialById(Integer id) {
        termFinancialDao.deleteTermFinancialById(id);
    }
}
