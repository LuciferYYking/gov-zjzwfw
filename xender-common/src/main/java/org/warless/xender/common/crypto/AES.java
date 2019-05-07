package org.warless.xender.common.crypto;

import org.warless.xender.common.utils.CommonUtils;
import org.warless.xender.common.utils.CryptoUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/7
 */
public class AES {

    private static byte[] AES(byte[] data, byte[] key, byte[] ivKey, int mode) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            SecretKeySpec secKey = new SecretKeySpec(secretKey.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance(CommonUtils.isEmpty(ivKey) ? "AES" : "AES/CBC/PKCS5Padding");
            if (CommonUtils.isEmpty(ivKey)) {
                cipher.init(mode, secKey);
            } else {
                cipher.init(mode, secKey, new IvParameterSpec(ivKey));
            }
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(byte[] data, byte[] key, byte[] ivKey) {
        return AES(data, key, ivKey, Cipher.ENCRYPT_MODE);
    }

    public static byte[] decrypt(byte[] data, byte[] key, byte[] ivKey) {
        return AES(data, key, ivKey, Cipher.DECRYPT_MODE);
    }

    public static String encrypt(String data, String key, String ivKey) {
        byte[] result = encrypt(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8), ivKey == null ? null : ivKey.getBytes(StandardCharsets.UTF_8));
        return result == null ? null : CryptoUtils.bytesToHexString(result);
    }

    public static String decrypt(String data, String key, String ivKey) {
        byte[] result = decrypt(CryptoUtils.hexStringToBytes(data), key.getBytes(StandardCharsets.UTF_8), ivKey == null ? null : ivKey.getBytes(StandardCharsets.UTF_8));
        return result == null ? null : new String(result, StandardCharsets.UTF_8);
    }

}
