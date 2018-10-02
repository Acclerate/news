package cc.momas.news.dao.impl;

import cc.momas.news.common.DataSource;
import cc.momas.news.dao.CommentDao;
import cc.momas.news.entity.Comment;
import cc.momas.news.entity.News;
import cc.momas.news.exception.BizException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public List<Comment> list(String sql) {
        // 获取数据库连接
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet result = pre.executeQuery();

            List<Comment> list = new ArrayList<>();

            while (result.next()) {
                Comment obj = new Comment();
                obj.setId(result.getInt("id"));
                obj.setContent(result.getString("content"));
                obj.setIp(result.getString("ip"));
                obj.setNewsId(result.getInt("news_id"));
                obj.setUserId(result.getInt("user_id"));
                obj.setCreatetime(result.getDate("createtime"));
                obj.setUpdatetime(result.getDate("updatetime"));
                obj.setStatus(result.getByte("status"));
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new BizException("数据库查询异常", e);
        }
    }

    @Override
    public void add(String sql, String[] params) {
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);

            for (int index = 0, len = params.length; index < len; index++) {
                pre.setString(index + 1, params[index]);
            }

            int result = pre.executeUpdate();
            if (result < 1) {
                throw new BizException("插入失败");
            }
        } catch (SQLException e) {
            throw new BizException("数据库查询异常", e);
        }
    }

    @Override
    public void update(String sql, String[] params) {
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);

            for (int index = 0, len = params.length; index < len; index++) {
                pre.setString(index + 1, params[index]);
            }

            int result = pre.executeUpdate();
            if (result < 1) {
                throw new BizException("更新失败");
            }
        } catch (SQLException e) {
            throw new BizException("数据库查询异常", e);
        }
    }

    @Override
    public void delete(String sql, Integer id) {
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, id);

            int result = pre.executeUpdate();
            if (result < 1) {
                throw new BizException("删除失败");
            }
        } catch (SQLException e) {
            throw new BizException("数据库查询异常", e);
        }
    }
}
