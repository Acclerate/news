package cc.momas.news.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cc.momas.news.common.BeanFactory;
import cc.momas.news.common.Constant;
import cc.momas.news.common.JsonUtil;
import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import cc.momas.news.service.UserService;

@WebServlet(value = { "/user" }, description = "用户相关Servlet", displayName = "UserServlet", name = "UserServlet")
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService = (UserService) BeanFactory.getBean(BeanFactory.SERVICE_USER);

	/**
	 * 查询一个用户 一般用于登录/查看用户信息
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收参数
		String pageStartString = request.getParameter("pageStart");
		String pageCountString = request.getParameter("pageCount");
		// 验证参数
		int pageStart = NumberUtils.toInt(pageStartString, 0); // 默认第一页,第0条
		int pageCount = NumberUtils.toInt(pageCountString, 10); // 默认一页1条
		// 调用业务
		List<User> list = userService.listPage(pageStart, pageCount);

		// 处理用户密码,避免泄露
		for (User user : list) {
			user.setPassword("[ PROTECTED ]");
		}

		// 返回结果
		String json = JsonUtil.toJson(list);
		response.getWriter().println(json);
	}

	/**
	 * 添加一个用户 一般用于注册
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 验证登录
		Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
		if (loginUserObject == null) {
			throw new BizException("请您登录后再添加用户");
		}
		Object isAdminObject = request.getSession().getAttribute(Constant.UserConstant.IS_ADMIN);
		if (null == isAdminObject) {
			throw new BizException("只有管理员可以添加用户");
		}

		// 接收参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String isAdminString = request.getParameter("isAdmin");

		// 验证参数
		// Blank 指空白字符,例如 \n,\r
		// Empty 指长度为0的字符串,就算是空白字符串也不算Empty
		if (StringUtils.isBlank(StringUtils.trim(username))) {
			throw new IllegalArgumentException("用户名不能空");
		}
		if (StringUtils.isBlank(StringUtils.trim(password))) {
			throw new IllegalArgumentException("密码不能为空");
		}
		if (password.length() < 6) {
			throw new IllegalArgumentException("密码长度不能小于6");
		}
		boolean isAdmin = "1".equals(isAdminString); // 1 表示是, 其他是否

		userService.registe(username, password, isAdmin, (User) loginUserObject);
	}

	/**
	 * 修改一个用户 一般用于修改个人资料
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 验证登录
		Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
		if (loginUserObject == null) {
			throw new BizException("请您登录后再修改用户资料");
		}

		// 接收参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String isAdminString = request.getParameter("isAdmin");
		String statusString = request.getParameter("status");

		// 验证参数
		Boolean isAdmin = "1".equals(isAdminString); // 1 表示是,其他为否
		// null 表示不修改数据库, 0 是有意义的,转换失败的时候将状态变为审核中
		Byte status = StringUtils.isBlank(statusString) ? null
				: NumberUtils.toByte(statusString, Constant.StatusConstant.AUDIT);

		userService.update(username, password, isAdmin, status, (User) loginUserObject);

	}

	/**
	 * 删除一个用户 一般用于用户删除
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 验证登录
		Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
		if (loginUserObject == null) {
			throw new BizException("请您登录后再删除用户");
		}
		Object isAdminObject = request.getSession().getAttribute(Constant.UserConstant.IS_ADMIN);
		if (null == isAdminObject) {
			throw new BizException("只有管理员可以删除用户");
		}
		// 接收参数
		String idString = request.getParameter("id");
		// 验证参数
		if (StringUtils.isBlank(idString)) {
			throw new IllegalArgumentException("未指定要删除的评论");
		}
		Integer id = NumberUtils.toInt(idString, -1);
		if (((User) loginUserObject).getId().equals(id)) {
			throw new BizException("用户不可删除自己");
		}
		userService.delete(id, (User) loginUserObject);
	}
}
