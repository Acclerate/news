package cc.momas.news.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

/**
 * 用来把 java 对象序列化成 json 的工具类
 * 
 * @author sothe
 *
 */
public abstract class JsonUtil {

	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	/**
	 * 把 java 对象序列化为 json 字符串
	 * @param javaObj java对象
	 * @return json 字符串
	 */
	public static String toJson(Object javaObj) {
		return gson.toJson(javaObj);
	}

	/**
	 * 把 json 字符串反序列化成 java 对象
	 * @param json json字符串
	 * @param clazz java对象的类
	 * @return java对象
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

}
