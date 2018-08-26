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

import org.apache.commons.lang3.StringUtils;

import cc.momas.news.common.Constant;

/**
 * 这个过滤器用于重定向,如果一个页面具有重定向的属性,则会重定向,否则不起作用
 * @author sothe
 *
 */
@WebFilter(value="/",displayName="RedirectFilter",description="用于处理请求完成后重定向的Filter")
public class RedirectFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(req, resp);

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 重定向到指定URL,如果没有指定则跳到首页
		String domain = request.getContextPath();
		String location = request.getParameter(Constant.REDIRECT);
		if (StringUtils.isNotBlank(location)) {
			response.sendRedirect(domain + location);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}
