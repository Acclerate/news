package cc.momas.news.dao;

import cc.momas.news.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> list(String sql);

    void insert(String sql, String[] params);

    void update(String sql, String[] params);

    void delete(String sql, Integer id);
}
