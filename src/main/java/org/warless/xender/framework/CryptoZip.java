package org.warless.xender.framework;

import org.warless.xender.framework.crypto.AES;
import org.warless.xender.utils.CryptoUtils;
import org.warless.xender.framework.zip.Zip;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/8
 */
public class CryptoZip implements ZipAdapter {

    private Zip zip;
    private String key;
    private String ivKey;

    public static final String TEMP_FILE = "temp.zip";

    public CryptoZip(Zip zip, String key, String ivKey) {
        this.zip = zip;
        this.key = CryptoUtils.padding(key, CryptoUtils.IV_LENGTH);
        this.ivKey = CryptoUtils.padding(ivKey, CryptoUtils.IV_LENGTH);
    }

    @Override
    public void compress(String src, String dest) throws IOException {
        zip.pack(src, TEMP_FILE);
        // encrypt
        AES.fileCrypto(TEMP_FILE, dest, key, ivKey, Cipher.ENCRYPT_MODE);
        clear();
    }

    @Override
    public void decompress(String src, String dest) throws IOException {
        // decrypt
        AES.fileCrypto(src, TEMP_FILE, key, ivKey, Cipher.DECRYPT_MODE);
        zip.unpack(TEMP_FILE, dest);
        clear();
    }

    private void clear() {
        File file = new File(TEMP_FILE);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public void setZip(Zip zip) {
        this.zip = zip;
    }

    public Zip getZip() {
        return zip;
    }

}
