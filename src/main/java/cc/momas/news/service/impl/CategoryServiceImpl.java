package cc.momas.news.service.impl;

import java.util.List;

import cc.momas.news.entity.Category;
import cc.momas.news.entity.User;
import cc.momas.news.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Integer parentId, String name, User oprator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Integer id, Integer parentId, String name, Byte status, User oprator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id, User oprator) {
		// TODO Auto-generated method stub

	}

}
