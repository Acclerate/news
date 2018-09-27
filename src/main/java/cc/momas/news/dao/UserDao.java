package cc.momas.news.dao;

import java.util.List;

import cc.momas.news.entity.User;

public interface UserDao {

	User login(String sql, String[] params);

	List<User> list(String sql, String[] params);

	void insert(String sql, String[] params);

	void update(String sql, String[] params);

	void delete(String sql, Integer id);

}
