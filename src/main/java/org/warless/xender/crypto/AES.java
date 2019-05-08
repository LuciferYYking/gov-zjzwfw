package org.warless.xender.crypto;

import org.warless.xender.util.CommonUtils;
import org.warless.xender.util.CryptoUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
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

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int nRead;
        while ((nRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, nRead);
            out.flush();
        }
    }

    public static void fileCrypto(String src, String dest, String key, String ivKey, int mode) throws IOException {
        if (mode != Cipher.DECRYPT_MODE && mode != Cipher.ENCRYPT_MODE) {
            throw new RuntimeException("Err Mode Code: " + mode);
        }
        File srcFile = new File(src);
        if (srcFile.exists() && srcFile.isFile()) {
            File destFile = new File(dest);
            if (destFile.getParentFile() != null && !destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
        }
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            SecretKeySpec secKey = new SecretKeySpec(secretKey.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(mode, secKey, new IvParameterSpec(ivKey.getBytes(StandardCharsets.UTF_8)));
            if (mode == Cipher.DECRYPT_MODE) {
                CipherOutputStream cout = new CipherOutputStream(out, cipher);
                write(in, cout);
                cout.close();
            }
            if (mode == Cipher.ENCRYPT_MODE) {
                CipherInputStream cin = new CipherInputStream(in, cipher);
                write(cin, out);
                cin.close();
            }
        } catch (NoSuchPaddingException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        in.close();
        out.close();
    }

}
