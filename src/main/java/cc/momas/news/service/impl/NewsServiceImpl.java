package cc.momas.news.service.impl;

import java.util.List;

import cc.momas.news.entity.News;
import cc.momas.news.entity.User;
import cc.momas.news.service.NewsService;

public class NewsServiceImpl implements NewsService {

	@Override
	public List<News> listNews(int pageStart, int pageCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String title, String summary, String content, Integer categoryId, User operator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id, User operator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String newsId, String title, String summary, String content, Integer categoryId, Byte status,
			User operator) {
		// TODO Auto-generated method stub

	}

}
