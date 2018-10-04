package cc.momas.news.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据源池
 * 
 * @author sothe
 *
 */
public class DataSource implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(DataSource.class);

	private static final String username = "root";
	private static final String password = "root";
	private static final String dbDriver = "com.mysql.cj.jdbc.Driver"; 
	private static final String dbUrl = "jdbc:mysql://localhost:3308/news?characterEncoding=UTF-8&useServerPrepStmts=true&cachePrepStmts=true&useSSL=false";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			log.error("数据库驱动初始化失败 | database driver init fail.", e);
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException e) {
			log.error("数据库连接失败 | database connect error.", e);
		}
		return null;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// nothing to do
	}
}
