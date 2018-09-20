package cc.momas.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.momas.news.common.BeanFactory;
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
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

	/**
	 * 用于新增新闻
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

	/**
	 * 用于修改新闻
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(request, response);
	}

	/**
	 * 用于删除新闻
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(request, response);
	}

}
