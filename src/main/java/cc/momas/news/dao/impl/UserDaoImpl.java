package cc.momas.news.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.momas.news.dao.UserDao;
import cc.momas.news.entity.User;
import cc.momas.news.exception.CoreException;

public class UserDaoImpl implements UserDao {

	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public User selectByIdAndPwd(Integer userId, String password) throws CoreException {
		
		return null;
	}

}
