package com.crazy.finance.service;

import com.crazy.finance.bean.ChangeMoney;

import java.util.List;

public interface ChangeMoneyService {
    List<ChangeMoney> selectAllChangeMoney();
    ChangeMoney selectChangeMoneyById(Integer id);

    void insertChangeMoney(ChangeMoney changeMoney);

    void deleteChangeMoneyById(Integer id);

    void updateChangeMoney(ChangeMoney changeMoney);
}
