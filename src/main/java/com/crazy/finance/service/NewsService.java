package com.crazy.finance.service;

import com.crazy.finance.bean.News;

import java.util.List;

public interface NewsService {
    List<News> selectAllNews();
}
