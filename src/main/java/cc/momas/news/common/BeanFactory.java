package cc.momas.news.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cc.momas.news.dao.impl.UserDaoImpl;

/**
 * 工具类应该是不可被实例化的,抽象的
 * @author sothe
 *
 */
public abstract class BeanFactory {

	/**	单例工厂 */
	public static final Map<String, Object> factory = new ConcurrentHashMap<String, Object>(8);

	// 数据访问层对象
	public static final String DAO_USER = "dao_user";
	public static final String DAO_CATEGORY = "dao_category";
	public static final String DAO_NEWS = "dao_news";
	public static final String DAO_COMMENT = "dao_comment";
	
	// 业务层对象
	public static final String SERVICE_USER = "userService";
	public static final String SERVICE_CATEGORY = "service_category";
	public static final String SERVICE_NEWS = "service_news";
	public static final String SERVICE_COMMENT = "service_comment";
//	public static final String DATA_SOURCE = "dataSource"; // 数据源
	
	static {
		
//		factory.put(DATA_SOURCE, new DataSource());
		
		// 先准备DAO,再准备service,因为Service中需要Dao
		// DAO
		factory.put(DAO_USER, new UserDaoImpl());

		// service
//		factory.put(SERVICE_USER, new UserServiceImpl());

	}

	public static Object getBean(String key) {
		return factory.get(key);
	}

}
