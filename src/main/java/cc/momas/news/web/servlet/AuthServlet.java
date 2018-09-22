package cc.momas.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.momas.news.common.BeanFactory;
import cc.momas.news.common.Constant;
import cc.momas.news.entity.User;
import cc.momas.news.service.UserService;

/**
 * 用于身份验证的Servlet
 * 
 * @author sothe
 *
 */
@WebServlet(value = { "/auth" }, description = "用于身份验证", displayName = "AuthServlet", name = "AuthServlet")
public class AuthServlet extends BaseServlet {

	private static final Logger log = LoggerFactory.getLogger(AuthServlet.class);
	private static final long serialVersionUID = 271092257646101316L;
	private static final UserService userService = (UserService) BeanFactory.getBean(BeanFactory.SERVICE_USER);
	
	/**
	 * 用于登录
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// 接收参数
		String userId = getParamReqired(Constant.UserConstant.USER_ID);
		String password = getParamReqired(Constant.UserConstant.PASSWORD);
		
		// 调用业务
		User currentUser = userService.login(Integer.valueOf(userId),password);// 登录失败会抛出业务异常
		
		// 登录成功把用户放进session里
		HttpSession session = request.getSession();
		session.setAttribute(Constant.UserConstant.LOGIN_USER, currentUser);
		// 保存用户的管理员状态
		boolean isAdmin = currentUser.getIsAdmin();
		if(isAdmin) {
			session.setAttribute(Constant.UserConstant.IS_ADMIN, isAdmin);
		}
		// TODO: 把用户信息放到cookies里
	}

	/**
	 * 用于注销登录
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("logout");
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate(); // 注销整个Session
		}
		// TODO:把cookies删除 
	}

}
