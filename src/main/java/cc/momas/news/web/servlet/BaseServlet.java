package cc.momas.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 所有的Servlet都以这个类为超类
 * 
 * @author sothereer@gmail.com
 *
 */
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 5291968243572163225L;
	private static final Logger log = LoggerFactory.getLogger(BaseServlet.class);
	
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
		log.debug("request parameter: key=[%s],value=[%s]", key, value); // 记录参数值
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
			throw new IllegalArgumentException("缺少参数  : " + key);
		}
		return value;
	}
}
