package com.crazy.finance.service.impl;

import com.crazy.finance.bean.Info;
import com.crazy.finance.dao.InfoDao;
import com.crazy.finance.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired(required = false)
    private InfoDao infoDao;

    @Override
    public List<Info> selectInfoByUserId(Integer userId) {
        return infoDao.selectInfoByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteInfoById(Integer infoId) {
         infoDao.deleteInfoById(infoId);
    }

    @Transactional
    @Override
    public void updateInfoStatusById(Integer infoId) {

           infoDao.updateInfoStatusById(infoId);
    }

    @Override
    public void insertInfo(Info info) {
        infoDao.insertInfo(info);
    }
}
