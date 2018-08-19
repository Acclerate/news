package cc.momas.news.service;

import javax.servlet.ServletException;

import cc.momas.news.entity.User;

public interface UserService {

	User login(Integer userId, String password) throws ServletException;

}
