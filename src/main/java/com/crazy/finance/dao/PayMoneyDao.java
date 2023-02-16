package com.crazy.finance.dao;

import com.crazy.finance.bean.PayMoney;

import java.util.List;

public interface PayMoneyDao {

    List<PayMoney> selectAllPayMoney();
}
