package cc.momas.news.dao;

import cc.momas.news.entity.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> list(String sql, String[] params);

    void add(String sql, String[] params);

    void update(String sql, String[] params);

    void delete(String sql, Integer id);
}
