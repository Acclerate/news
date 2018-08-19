package cc.momas.news.service.impl;

import javax.servlet.ServletException;

import cc.momas.news.common.BeanFactory;
import cc.momas.news.dao.UserDao;
import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import cc.momas.news.exception.CoreException;
import cc.momas.news.service.UserService;

public class UserServiceImpl implements UserService {

	private static final UserDao userDao = (UserDao) BeanFactory.getBean(BeanFactory.DAO_USER);

	@Override
	public User login(Integer userId, String password) throws ServletException {
		User user = null;
		try {
			user = userDao.selectByIdAndPwd(userId, password);
		} catch (CoreException e) {
			// TODO : 这里很不自然,应该使用更合适的异常
			throw new ServletException(e);
		}
		if (user == null) {
			throw new BizException("用户不存在或密码错误");
		}
		return user;
	}

}
