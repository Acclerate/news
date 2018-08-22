package cc.momas.news.common;

import static org.junit.Assert.assertEquals;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Md5Crypto {

	@Test
	public void md5Hex() {
		String pwd = DigestUtils.md5Hex("123456".getBytes());
		// 验证MD5正确计算
		assertEquals(pwd, "e10adc3949ba59abbe56e057f20f883e");
	}
}
