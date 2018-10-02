package cc.momas.news.dao.impl;

import cc.momas.news.common.DataSource;
import cc.momas.news.dao.CategoryDao;
import cc.momas.news.entity.Category;
import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static final Logger log = LoggerFactory.getLogger(CategoryDaoImpl.class);

    @Override
    public List<Category> list(String sql) {

        log.info("execute sql is : {}", sql);

        // 获取数据库连接
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet result = pre.executeQuery();

            List<Category> list = new ArrayList<>();

            while (result.next()) {
                Category obj = new Category();
                obj.setId(result.getInt("id"));
                obj.setCreatetime(result.getDate("createtime"));
                obj.setUpdatetime(result.getDate("updatetime"));
                obj.setName(result.getString("name"));
                obj.setParentId(result.getInt("parent_id"));
                obj.setStatus(result.getByte("status"));
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new BizException("数据库查询异常", e);
        }
    }

    @Override
    public void insert(String sql, String[] params) {

        log.info("execute sql is : {}, params is {}", sql,params);

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
        log.info("execute sql is : {}, params is {}", sql,params);
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
        log.info("execute sql is : {}, params is {}", sql,id);
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
