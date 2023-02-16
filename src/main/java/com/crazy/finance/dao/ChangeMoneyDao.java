package com.crazy.finance.dao;

import com.crazy.finance.bean.ChangeMoney;
import javafx.scene.control.TextFormatter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChangeMoneyDao {

    List<ChangeMoney> selectAllChangeMoney();

    ChangeMoney selectChangeMoneyById(Integer id);


    void insertChangeMoney(@Param("changeMoney") ChangeMoney changeMoney);

    void deleteChangeMoneyById(Integer id);

    void updateChangeMoney(@Param("changeMoney") ChangeMoney changeMoney);
}
