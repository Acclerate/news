package cc.momas.news.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理日期的工具类
 */
public abstract class DateUtil {

    /**
     * 用于转换的工具类
     */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期的字符串 例: "2018-10-04 09:55:41"
     * @return
     */
    public static String getNowString() {
        return dateFormat.format(new Date());
    }
}
