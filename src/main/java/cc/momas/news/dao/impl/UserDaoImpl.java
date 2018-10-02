package cc.momas.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cc.momas.news.common.DataSource;
import cc.momas.news.dao.UserDao;
import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoImpl implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User login(String sql, String[] params) {
        log.info("execute sql is : {}, params is {}", sql, params);
        // 获取数据库连接
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                pre.setString(i + 1, params[i]);
            }

            ResultSet result = pre.executeQuery();

            User user = null;

            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setCreatetime(result.getDate("createtime"));
                user.setUpdatetime(result.getDate("updatetime"));
                user.setIsAdmin(result.getBoolean("is_admin"));
                user.setStatus(result.getByte("status"));
            }
            return user;
        } catch (SQLException e) {
            throw new BizException("数据库查询异常", e);
        }
    }

    @Override
    public List<User> list(String sql, String[] params) {
        log.info("execute sql is : {}, params is {}", sql, params);
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);

            for (int index = 0, len = params.length; index < len; index++) {
                pre.setString(index + 1, params[index]);
            }

            ResultSet result = pre.executeQuery();

            User user = null;
            List<User> list = new ArrayList<>();

            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setCreatetime(result.getDate("createtime"));
                user.setUpdatetime(result.getDate("updatetime"));
                user.setIsAdmin(result.getBoolean("is_admin"));
                user.setStatus(result.getByte("status"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new BizException("数据库查询异常", e);
        }
    }

    @Override
    public void insert(String sql, String[] params) {
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
    public void delete(String sql, Integer id) {
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
