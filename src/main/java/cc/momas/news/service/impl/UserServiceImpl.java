package cc.momas.news.service.impl;

import java.util.Date;
import java.util.List;

import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import cc.momas.news.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User login(Integer userId, String password) throws BizException {
		// TODO Auto-generated method stub
		if(Integer.valueOf(123).equals(userId) && "123".equals(password)) {
			User user = new User();
			user.setId(123);
			user.setCreatetime(new Date());
			user.setIsAdmin(true);
			user.setPassword("123");
			user.setStatus((byte)0);
			user.setUsername("123");
			return user;
		}
		throw new BizException("登录失败");
	}

	@Override
	public List<User> listPage(int pageStart, int pageCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registe(String username, String password, boolean isAdmin, User oprator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String username, String password, Boolean isAdmin, Byte status, User oprator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id, User oprator) {
		// TODO Auto-generated method stub

	}

}
