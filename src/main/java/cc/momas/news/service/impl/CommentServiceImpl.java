package cc.momas.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import cc.momas.news.common.BeanFactory;
import cc.momas.news.dao.CommentDao;
import cc.momas.news.entity.Comment;
import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import cc.momas.news.service.CommentService;
import org.apache.commons.lang3.StringUtils;

public class CommentServiceImpl implements CommentService {

    CommentDao commentDao = (CommentDao) BeanFactory.getBean(BeanFactory.DAO_COMMENT);

    @Override
    public List<Comment> listPage(int pageStart, int pageCount) {
        String sql = "SELECT `id`,`content`,`ip`,`news_id`,`user_id`,`createtime`,`updatetime`,`status` FROM news.`comment` LIMIT ?,?";
        String[] params = {String.valueOf(pageStart), String.valueOf(pageCount)};
        List<Comment> list = commentDao.list(sql, params);
        return list;
    }

    @Override
    public void add(String content, String ip, Integer newsId, User oprator) {
        String sql = "INSERT INTO `news`.`comment`" +
                " (`content`, `ip`, `news_id`, `user_id`, `createtime`, `updatetime`, `status`) " +
                "VALUES (?, ?, ?, ?, NOW(), NOW(), '0')";
        String[] params = {content, ip, String.valueOf(newsId), String.valueOf(oprator.getId())};
        commentDao.add(sql, params);
    }

    @Override
    public void update(Integer id, String content, String ip, Byte status, User oprator) {
        // 参数验证
        if (!oprator.getIsAdmin()) {
            // 非管理员操作
            throw new BizException("您没有权限,您配吗?");
        }
        String prefix = "UPDATE `news`.`comment` SET ";
        String suffix = "WHERE (`id` = ?)";
        StringBuilder sql = new StringBuilder();
        List<String> paramList = new ArrayList<>();

        if (StringUtils.isNoneBlank(content)) {
            sql.append(" `content` = ?");
            paramList.add(content);
        }
        if (StringUtils.isNoneBlank(ip)) {
            if (sql.length() > 0) {
                sql.append(",");
            }
            sql.append(" `ip` = ?");
            paramList.add(content);
        }

        if (sql.length() > 0) {
            sql.append(",");
        }
        sql.append(" `updatetime` = NOW()");

        if (null != status) {
            if (sql.length() > 0) {
                sql.append(",");
            }
            sql.append(" `status` = ?");
            paramList.add(String.valueOf(status));
        }
        paramList.add(String.valueOf(id));
        commentDao.update(sql.toString(), (String[]) paramList.toArray());
    }

    @Override
    public void delete(Integer id, final User oprator) {
        // 参数验证
        if (!oprator.getIsAdmin()) {
            // 非管理员操作
            throw new BizException("您没有权限,您配吗?");
        }
        if (null == id || id < 0) {
            return;
        }
        String sql = "DELETE FROM `comment` WHERE id = ?";
        commentDao.delete(sql, id);
    }

}
