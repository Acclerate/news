package cc.momas.news.service.impl;

import javax.servlet.ServletException;

import org.apache.commons.codec.digest.DigestUtils;

import cc.momas.news.common.BeanFactory;
import cc.momas.news.dao.UserDao;
import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import cc.momas.news.service.UserService;

public class UserServiceImpl implements UserService {

	private static final UserDao userDao = (UserDao) BeanFactory.getBean(BeanFactory.DAO_USER);

	@Override
	public User login(Integer userId, String password) throws ServletException {
		password = DigestUtils.md5Hex(password.getBytes()); // md5加密一下
		User user = userDao.selectByIdAndPwd(userId, password);
		if (user == null) {
			throw new BizException("用户不存在或密码错误");
		}
		return user;
	}

}
