package cc.momas.news.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * <code>
 * https://blog.csdn.net/mmd1234520/article/details/70210002
 * </code>
 * <code>
 * http://mvnrepository.com/artifact/org.apache.commons/codec/1.7.0
 * </code>
 * @author sothe
 *
 */
public abstract class DigestUtil {

	public static String md5Hex(String src) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ignore) {
			// 不可能发生
		}
		md.update(src.getBytes());// update处理
		byte[] encryContext = md.digest();// 调用该方法完成计算

		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < encryContext.length; offset++) {// 做相应的转化（十六进制）
			i = encryContext[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
//       System.out.println("32result: " + buf.toString()); // 32位的加密  
//       System.out.println("16result: " + buf.toString().substring(8, 24)); // 16位的加密
	}
}
