package cc.momas.news.service;

import static org.junit.Assert.assertNotNull;

import javax.servlet.ServletException;

import org.junit.Test;

import cc.momas.news.common.BeanFactory;
import cc.momas.news.entity.User;

public class UserServiceTest {

	/**
	 *  测试用户登录(管理员)
	 * @throws ServletException 数据库异常时
	 */
	@Test
	public void adminLogin() throws ServletException {
		User admin ;
		UserService svc = (UserService) BeanFactory.getBean(BeanFactory.SERVICE_USER);
		admin = svc.login(1, "admin");
		admin = svc.login(1, "admin");
		System.out.println(admin);
		assertNotNull("管理员不存在或密码被更改",admin);
	}
}
