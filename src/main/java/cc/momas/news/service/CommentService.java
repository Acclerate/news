package cc.momas.news.service;

import java.util.List;

import cc.momas.news.entity.Comment;
import cc.momas.news.entity.User;

public interface CommentService {

	/**
	 * 获取 comment 列表
	 *
     * @param newsId 新闻id,表示这个评论属于哪个新闻,可空
     * @param pageStart 分页起始下标,指定每一页第一个的下标
     * @param pageCount 分布容量,指定每页多少行数据
     * @return comment列表
	 */
	List<Comment> listPage(Integer newsId, Integer pageStart, Integer pageCount);

	/**
	 * 添加一个 comment
	 * @param content comment内容,不可为空字符串
	 * @param ip 用户ip地址
	 * @param newsId 被评论的 news 的 id
	 * @param oprator 操作的用户
	 */
	void add(String content, String ip, Integer newsId, User oprator);
	
	/**
	 * 更新一个 comment
	 * @param id 被更新的 commnet 的 id
	 * @param content 更新的内容
	 * @param ip 用户的ip地址
	 * @param status commnet 状态
	 * @param oprator 操作的用户
	 */
	void update(Integer id, String content, String ip, Byte status, User oprator);

	/**
	 * 删除 commnet
	 * @param id 被删除的 comment 的 id
	 * @param oprator 操作的用户
	 */
	void delete(Integer id, User oprator);

	

}
