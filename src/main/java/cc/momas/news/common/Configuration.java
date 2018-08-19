package cc.momas.news.common;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public abstract class Configuration {

	{
		loadProps();
	}

	protected static Properties props = new Properties();

	protected static ResourceBundle res = ResourceBundle.getBundle("database");

	public static String getDbUrl() {
		return props.getProperty("database.url", "jdbc:mysql://localhost:3306/news");
	}

	public static String getDbUser() {
		return props.getProperty("database.user", "root");
	}

	public static String getDbPassword() {
		return props.getProperty("database.password", "root");
	}

	public static String getDbDriver() {
		return props.getProperty("database.driver", "com.mysql.jdbc.Driver");
	}

	/**
	 * 从文件中加载配置
	 */
	private void loadProps() {
		Enumeration<String> keys = res.getKeys();
		Properties tmp = new Properties();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			tmp.setProperty(key, res.getString(key));
		}
		props = tmp;
	}
}
