package cc.momas.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cc.momas.news.common.DataSource;
import cc.momas.news.dao.UserDao;
import cc.momas.news.entity.User;
import cc.momas.news.exception.CoreException;

public class UserDaoImpl implements UserDao {

	@Override
	public User selectByIdAndPwd(Integer userId, String password) throws CoreException {
		// 获取数据库连接
		try (Connection conn = DataSource.getConnection()) {
			String sql = "SELECT id,username,`password`,createtime,updatetime,is_admin,`status` FROM `user` WHERE id=? AND password=?";
			PreparedStatement pre = conn.prepareStatement(sql);

			int index = 0;
			pre.setInt(++index, userId);
			pre.setString(++index, password);

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
			throw new CoreException("数据库查询异常", e);
		}
	}

}
