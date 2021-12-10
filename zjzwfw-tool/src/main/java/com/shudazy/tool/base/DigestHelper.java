package com.shudazy.tool.base;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/***
 * 摘要加密算法辅助类
 *
 * @author bingdyee
 * @since 2021/12/09
 */
public final class DigestHelper {

	private static final String HMAC_SHA256 = "HmacSHA256";

	/***
	 * 获取请求签名值
	 * 
	 * @param data 加密前数据
	 * @param key 密钥
	 * @return HMAC加密后16进制字符串
	 */
	public static String hmacSha256(String data, String key) {
		try {
			Mac mac = Mac.getInstance(HMAC_SHA256);
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
			mac.init(secretKey);
			mac.update(data.getBytes(StandardCharsets.UTF_8));
			byte [] digest = mac.doFinal();
			return new String(Base64.getEncoder().encode(digest), StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException("签名失败");
		}
	}

	/**
	 * MD5
	 *
	 * @param data plain text
	 * @return md5 hex
	 */
	public static String md5hex(String data) {
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance("MD5").digest(data.getBytes());
		} catch (NoSuchAlgorithmException ignored) {}
		return byte2hex(bytes);
	}

	/***
	 * 将byte[]转成16进制字符串
	 * 
	 * @param data byte[]
	 * 
	 * @return 16进制字符串
	 */
	public static String byte2hex(byte[] data) {
		StringBuilder hash = new StringBuilder();
		String stmp;
		for (int n = 0; data != null && n < data.length; n++) {
			stmp = Integer.toHexString(data[n] & 0XFF);
			if (stmp.length() == 1) {
				hash.append('0');
			}
			hash.append(stmp);
		}
		return hash.toString();
	}

}
