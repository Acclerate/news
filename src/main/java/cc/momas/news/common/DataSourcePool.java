package cc.momas.news.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据源池
 * 
 * @author sothe
 *
 */
public abstract class DataSourcePool {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(Configuration.getDbDriver());
		return DriverManager.getConnection(Configuration.getDbUrl(), Configuration.getDbUser(),
				Configuration.getDbPassword());
	}
}
