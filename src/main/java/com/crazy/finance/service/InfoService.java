package com.crazy.finance.service;

import com.crazy.finance.bean.Info;

import java.util.List;

public interface InfoService {

    List<Info> selectInfoByUserId(Integer userId);

    void deleteInfoById(Integer infoId);

    void updateInfoStatusById(Integer userId);

    void insertInfo(Info info);
}
