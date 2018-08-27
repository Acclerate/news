package cc.momas.news.common;

/**
 * 
 * 用来保存项目所有常量/参数名
 * 用于统一参数名
 * 
 * 
 * @author sothe
 *
 */
public interface Constant {

	// 重定向参数名
	String REDIRECT = "redirect";

	/**
	 * 这个接口只用来存放项目使用的常量
	 * 
	 * @author sothe
	 *
	 */
	public interface UserConstant {

		// 用户id
		String USER_ID = "uid";
		// 密码
		String PASSWORD = "pwd";
		// 用户名
		String USERNAME = "uname";
		// 登录用户
		String LOGIN_USER = "loginUser";
		// 是管理员
		String IS_ADMIN = "isAdmin";
	}

	public interface ErrorPath{
		String SERVER_ERROR_PAGE = "/error.html";
	}
}