package cc.momas.news.service.impl;

import java.util.List;

import cc.momas.news.entity.Comment;
import cc.momas.news.entity.User;
import cc.momas.news.service.CommentService;

public class CommentServiceImpl implements CommentService{

	@Override
	public List<Comment> listPage(int pageStart, int pageCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String content, String ip, Integer newsId, User oprator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Integer id, String content, String ip, Byte status, User oprator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id, User oprator) {
		// TODO Auto-generated method stub
		
	}

}
