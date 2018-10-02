package cc.momas.news.dao;

import cc.momas.news.entity.News;

import java.util.List;

public interface NewsDao {

    List<News> list(String sql, String [] params);

    void add(String sql, String[] params);

    void update(String sql, String[] params);

    void datele(String sql, Integer id);
}
