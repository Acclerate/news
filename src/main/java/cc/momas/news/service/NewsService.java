package cc.momas.news.service;

import java.util.List;

import cc.momas.news.entity.News;
import cc.momas.news.entity.User;

public interface NewsService {
	
	/**
	 * 分页查询新闻
	 * @param pageStart 分页起始下标,指定每一页第一个的下标
	 * @param pageCount 分布容量,指定每页多少行数据
	 * @return news 列表
	 */
	List<News> listNews(int pageStart, int pageCount);
	/**
	 *  添加一个news
	 * @param title news 的标题 
	 * @param summary news的摘要,可空
	 * @param content news的内容,不可空
	 * @param categoryId news的分类id,不可空
	 * @param operator 操作者,如果是必须是管理员才能使用
	 */
	void add(String title, String summary, String content, Integer categoryId, User operator);

	/**
	 * 删除一个news
	 * @param id news的id
	 * @param operator 操作者,必须是管理员才能删除 
	 */
	void delete(Integer id, User operator);

	/**
	 * 更新一个news,字段为null表示不修改
	 * @param newsId news的id,不可空
	 * @param title news的标题
	 * @param summary news的摘要
	 * @param content news的内容
	 * @param categoryId 新闻分类id
	 * @param status 新闻分类id
	 * @param operator 操作者需要管理员才能修改
	 */
	void update(String newsId, String title, String summary, String content, Integer categoryId, Byte status, User operator);

}
