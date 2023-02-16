package com.crazy.finance.dao;

import com.crazy.finance.bean.News;

import java.util.List;

public interface NewsDao {

    List<News> selectAllNews();
}