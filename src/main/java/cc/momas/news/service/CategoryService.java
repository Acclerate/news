package cc.momas.news.service;

import java.util.List;

import cc.momas.news.entity.Category;
import cc.momas.news.entity.User;

public interface CategoryService {

	/**
	 * 获取所有 category
	 * @return category 列表
	 */
	List<Category> list();

	/**
	 * 添加一个 category
	 * @param parentId category 父id
	 * @param name category 名称
	 * @param oprator 操作的用户
	 */
	void add(Integer parentId, String name, User oprator);

	/**
	 * 更新一个 category
	 * @param id category 的标识符,不能为空
	 * @param parentId category的父id,修改时表示将此category移动到其他分类下
	 * @param name category的名称
	 * @param status category的状态,0表示正常,负数表示异常
	 * @param oprator 操作的用户
	 */
	void update(Integer id, Integer parentId, String name, Byte status, User oprator);

	/**
	 * 删除一个 category 
	 * @param id category的id,不能为空
	 * @param oprator 操作的用户
	 */
	void delete(Integer id, User oprator);

}
