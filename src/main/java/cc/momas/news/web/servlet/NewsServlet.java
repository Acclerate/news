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
import cc.momas.news.entity.News;
import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import cc.momas.news.service.NewsService;

@WebServlet(value = { "/news" }, description = "新闻相关", displayName = "NewsServlet", name = "NewsServlet")
public class NewsServlet extends BaseServlet {

	private static final long serialVersionUID = 4760738305339469459L;
	private NewsService newsService = (NewsService)BeanFactory.getBean(BeanFactory.SERVICE_NEWS);
	
	/**
	 * 用于获取新闻
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收参数
		String pageStartString = request.getParameter("pageStart");
		String pageCountString = request.getParameter("pageCount");
		// 验证参数
		int pageStart = NumberUtils.toInt(pageStartString, 0); // 默认第一页,第0条
		int pageCount = NumberUtils.toInt(pageCountString, 10); // 默认一页10条
		// 调用业务
		List<News> list = newsService.listNews(pageStart,pageCount);
		response.getWriter().println(JsonUtil.toJson(list));;
	}

	/**
	 * 用于新增新闻
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 验证登录
		Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
		if (loginUserObject == null) {
			throw new BizException("请您登录后再发表新闻");
		}

		// 接收参数
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		String categoryIdString = request.getParameter("categoryId");

		// 验证参数
		// Blank 指空白字符,例如 \n,\r
		// Empty 指长度为0的字符串,就算是空白字符串也不算Empty
		if (StringUtils.isBlank(StringUtils.trim(title))) {
			throw new IllegalArgumentException("没有标题");
		}
		if (StringUtils.isBlank(StringUtils.trim(content))) {
			throw new IllegalArgumentException("新闻内容为空");
		}
		// 会自动先判断非空
		if (!StringUtils.isNumeric(categoryIdString)) {
			throw new IllegalArgumentException("新闻类型id未指定");
		}
		Integer categoryId = NumberUtils.toInt(categoryIdString);

		newsService.add(title, summary, content, categoryId, (User) loginUserObject);
	}

	/**
	 * 用于修改新闻
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 验证登录
		Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
		if (loginUserObject == null) {
			throw new BizException("请您登录后再修改新闻");
		}
		
		// 接收参数
		String newsId = request.getParameter("newsId");
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		String categoryIdString = request.getParameter("categoryId");
		String statusString = request.getParameter("status");

		// 验证参数
		// Blank 指空白字符,例如 \n,\r
		// Empty 指长度为0的字符串,就算是空白字符串也不算Empty
		if (!StringUtils.isNumeric(newsId)) {
			throw new IllegalArgumentException("新闻id未指定");
		}
		// null 表示不修改数据库, 0 是有意义的,转换失败的时候将状态变为审核中
		Integer categoryId = StringUtils.isBlank(categoryIdString) ? null : NumberUtils.toInt(categoryIdString, 0);
		Byte status = StringUtils.isBlank(statusString) ? null : NumberUtils.toByte(statusString, Constant.StatusConstant.AUDIT);

		newsService.update(newsId, title, summary, content, categoryId, status, (User) loginUserObject);
	}

	/**
	 * 用于删除新闻
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 验证登录
		Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
		if (loginUserObject == null) {
			throw new BizException("请您登录后再删除新闻");
		}

		// 接收参数
		String idString = request.getParameter("id");
		// 验证参数
		if (StringUtils.isBlank(idString)) {
			throw new IllegalArgumentException("未指定要删除的新闻");
		}
		Integer id = NumberUtils.toInt(idString, -1);
		newsService.delete(id, (User) loginUserObject);
	}

}
