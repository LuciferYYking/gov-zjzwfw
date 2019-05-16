package org.warless.xender.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * CryptoUtils
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/7
 */
public class CryptoUtils {

    public static final String MD5 = "MD5";
    public static final String SHA = "SHA";
    public static final String AES = "AES";
    public static final Integer IV_LENGTH = 16;
    public static final String PADDING_CHAR = "\0";

    private static final String HEX_STR =  "0123456789ABCDEF";

    /**
     *
     * 将二进制转换为十六进制字符输出
     *
     * @param bytes bytes数组
     * @return 十六进制字符
     */
    public static String bytesToHexString(byte[] bytes) {
        if(bytes== null ){
            return null;
        }
        String result = "";
        String hex;
        for (byte aByte : bytes) {
            // 字节高4位
            hex = String.valueOf(HEX_STR.charAt((aByte & 0xF0) >> 4));
            // 字节低4位
            hex += String.valueOf(HEX_STR.charAt(aByte & 0x0F));
            result += hex;
        }
        return result;
    }

    /**
     *
     * 将十六进制转换为字节数组
     *
     * @param hexString 十六进制字符
     * @return bytes数组
     */
    public static byte[] hexStringToBytes(String hexString) {
        if(hexString == null ){
            return null;
        }
        // hexString的长度对2取整，作为bytes的长度
        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        // 字节高四位、低四位
        byte high, low;
        for (int i = 0; i < len; i++) {
            // 右移四位得到高位
            high = (byte) ((HEX_STR.indexOf(hexString.charAt(2 * i))) << 4);
            low = (byte) HEX_STR.indexOf(hexString.charAt(2 * i + 1));
            //高地位做或运算
            bytes[i] = (byte) (high | low);
        }
        return bytes;
    }


    public static String encodeWithMD5(String content, String salt) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            byte[] data = content.getBytes(StandardCharsets.UTF_8);
            MessageDigest md5 = MessageDigest.getInstance(MD5);
            md5.update(data);
            md5.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] result = md5.digest();
            return bytesToHexString(result);
        } catch (NoSuchAlgorithmException e0) {
            e0.printStackTrace();
        }
        return null;
    }

    public static String encodeWithMD5(String content) {
        return encodeWithMD5(content, "");
    }

    public static String SHA256(String path) {
        byte[] partialHash = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[2048];
            int read;
            while ((read = bis.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, read);
            }
            bis.close();
            partialHash = messageDigest.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return bytesToHexString(partialHash);
    }

    public static byte[] padding(byte[] bytes, int len) {
        if (CommonUtils.isEmpty(bytes) || len < 1) {
            return null;
        }
        int n = bytes.length % len;
        for (int i = 0; i < n ; ++i) {
        }
        return null;
    }

    public static String padding(String iv, int len) {
        if (StringUtils.isBlank(iv) || len < 1) {
            return null;
        }
        int n = iv.length() % len;
        for (int i = 0; i < n ; ++i) {
            iv += PADDING_CHAR;
        }
        System.err.println();
        return iv;
    }

}
