package cc.momas.news.common;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import cc.momas.news.common.DataSource;

public class DaoTest {

	/**
	 * 测试数据库连通性
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void getConnectionTest() throws ClassNotFoundException, SQLException {
		DataSource ds = new DataSource();
		Connection conn = ds.getConnection();
		System.out.println(conn);
		assertNotNull(conn);
	}

}
