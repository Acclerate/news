package cc.momas.news.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.momas.news.common.Constant;
import cc.momas.news.exception.BizException;

/**
 * 统一处理异常的Filter
 * 
 * 当请求发生异常的时候,由这个Filter统一处理,
 * 
 * @author sothereer@gmail.com
 */
@WebFilter(value = { "/*" })
public class ExceptionFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(ExceptionFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (BizException e) {
			// BizException 一般由于业务层执行时出的异常,也就是业务临时不可用
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,e.getMessage());
			log.debug("BizException",e);
		} catch (RuntimeException | ServletException e) {
			// RuntimeException 是参数非法的异常
			// ServletException 一般由于Servlet层验证参数失败导致,也就是参数非法
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
			log.debug("runtime or servlet exceptioin" , e);
		} catch (Exception e) {
			// 重定向到错误页面
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + Constant.ErrorPath.SERVER_ERROR_PAGE);
			log.error("", e);
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
