package cc.momas.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import cc.momas.news.common.BeanFactory;
import cc.momas.news.dao.NewsDao;
import cc.momas.news.entity.News;
import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import cc.momas.news.service.NewsService;
import org.apache.commons.lang3.StringUtils;

public class NewsServiceImpl implements NewsService {

    private static final NewsDao newsDao = (NewsDao) BeanFactory.getBean(BeanFactory.DAO_NEWS);

    @Override
    public List<News> listNews(int pageStart, int pageCount) {
        String sql = "SELECT `id`,`title`,`summary`,`content`,`category_id`,`user_id`,`createtime`,`updatetime`,`status` FROM news.`news` LIMIT ?,?;";
        String[] params = {String.valueOf(pageStart), String.valueOf(pageCount)};
        List<News> list = newsDao.list(sql, params);
        return list;
    }

    @Override
    public void add(String title, String summary, String content, Integer categoryId, User operator) {
        // 参数验证
        if (!operator.getIsAdmin()) {
            // 非管理员操作
            throw new BizException("您没有权限,您配吗?");
        }

        String sql = "INSERT INTO `news` (`title`, `summary`, `content`, `category_id`, `user_id`, `createtime`, `updatetime`, `status`) " +
                "VALUES (?, ?, ?, ?, ?, NOW(), NOW(), '0')";
        String[] params = {title, summary, content, String.valueOf(categoryId), String.valueOf(operator.getId())};
        newsDao.add(sql, params);
    }

    @Override
    public void delete(Integer id, User operator) {
        // 参数验证
        if (!operator.getIsAdmin()) {
            // 非管理员操作
            throw new BizException("您没有权限,您配吗?");
        }
        // sql
        String sql = "DELETE FROM `news` WHERE id=?";
        // 返回值
        newsDao.datele(sql, id);
    }

    @Override
    public void update(String newsId, String title, String summary, String content, Integer categoryId, Byte status,
                       User operator) {
        // 参数验证
        if (!operator.getIsAdmin()) {
            // 非管理员操作
            throw new BizException("您没有权限,您配吗?");
        }

        StringBuilder sql = new StringBuilder();
        String prefix = "UPDATE `news` SET ";
        String suffix = "WHERE (`id` = ?)";
        List<String> paramList = new ArrayList<>();

        if (null != title) {
            sql.append(" `title` = ?");
            paramList.add(title);
        }
        if (StringUtils.isNoneBlank(summary)) {
            if (sql.length() > 0) {
                sql.append(",");
            }
            sql.append(" `summary` = ?");
            paramList.add(summary);
        }
        if (null != categoryId) {
            if (sql.length() > 0) {
                sql.append(",");
            }
            sql.append(" `category_id` = ?");
            paramList.add(summary);
        }
        if (null != status) {
            if (sql.length() > 0) {
                sql.append(",");
            }
            sql.append(" `status` = ?");
            paramList.add(summary);
        }

        if (StringUtils.isNoneBlank(summary)) {
            if (sql.length() > 0) {
                sql.append(",");
            }
            sql.append(" `updatetime` = NOW()");
            paramList.add(summary);
        }
        sql = sql.insert(0,prefix).append(suffix);
        newsDao.update(sql.toString(), (String[]) paramList.toArray());
    }

}
