package cc.momas.news.dao;

import cc.momas.news.entity.User;
import cc.momas.news.exception.CoreException;

public interface UserDao {

	User selectByIdAndPwd(Integer userId, String password) throws CoreException;

}
