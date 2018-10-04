package cc.momas.news.dao.impl;

import cc.momas.news.common.DataSource;
import cc.momas.news.dao.NewsDao;
import cc.momas.news.entity.News;
import cc.momas.news.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {


    private static final Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);

    @Override
    public List<News> list(String sql, String[] params) {
        log.info("execute sql is : {}, params is {}", sql, params);
        // 获取数据库连接
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);

            for (int index = 0; index < params.length; index++) {
                pre.setString(index + 1, params[index]);
            }

            ResultSet result = pre.executeQuery();

            List<News> list = new ArrayList<>();

            while (result.next()) {
                News obj = new News();
                obj.setId(result.getInt("id"));
                obj.setTitle(result.getString("title"));
                obj.setSummary(result.getString("summary"));
                obj.setContent(result.getString("content"));
                obj.setCategoryId(result.getInt("category_id"));
                obj.setUserId(result.getInt("user_id"));
                obj.setUpdatetime(result.getTimestamp("updatetime"));
                obj.setCreatetime(result.getTimestamp("createtime"));
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
        log.info("execute sql is : {}, params is {}", sql, params);
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
        log.info("execute sql is : {}, params is {}", sql, params);
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
    public void datele(String sql, Integer id) {
        log.info("execute sql is : {}, params is {}", sql, id);
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
