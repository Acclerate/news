package cc.momas.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.momas.news.exception.BizException;

/**
 * 所有的Servlet都以这个类为超类
 * 
 * 其实这个类也就是添加了两个取参数的方法，比较方便逻辑编写,和从HttpServlet直接继承没有太大区别
 * 
 * @author sothereer@gmail.com
 *
 */
public abstract class BaseServlet extends HttpServlet {

//	private static final Logger log = LoggerFactory.getLogger(BaseServlet.class);
	private static final long serialVersionUID = -2161590059800164478L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.request = req;
		this.response = resp;
		super.service(req, resp);
	}
	
	/**
	 * 获取请求参数
	 * 
	 * @param key
	 *            参数名
	 * @return 参数值
	 */
	protected String getParam(String key) {
		String value = this.request.getParameter(key);
//		log.debug("request parameter: key=[%s],value=[%s]", key, value); // 记录参数值
		return StringUtils.trim(value);
	}

	/**
	 * 获取请求必选参数
	 * 
	 * @param key
	 *            参数名
	 * @return 参数值
	 */
	protected String getParamReqired(String key) {
		String value = getParam(key);
		if (StringUtils.isEmpty(value)) {
			throw new BizException("缺少参数  : " + key);
		}
		return value;
	}
}
