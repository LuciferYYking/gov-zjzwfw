package org.warless.xender.test.uitls;

import org.junit.Test;
import org.warless.xender.utils.CryptoUtils;
import org.warless.xender.framework.CryptoZip;
import org.warless.xender.framework.zip.Zip;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/8
 */
public class CryptoZipTest {

    private String KEY = "1234567890";
    private String IV_KEY = "123456789";

    @Test
    public void compress() throws IOException {
        String src = "E:\\workspaces\\temp\\files";
        String dest = "E:\\workspaces\\temp\\A.zip";
        Zip zip = new Zip(StandardCharsets.UTF_8);

        CryptoZip cryptoZip = new CryptoZip(zip, KEY, IV_KEY);
        cryptoZip.compress(src, dest);
    }

    @Test
    public void decompress() throws IOException {
        String src = "E:\\workspaces\\temp\\A.zip";
        String dest = "E:\\workspaces\\temp\\A";
        Zip zip = new Zip(StandardCharsets.UTF_8);
        CryptoZip cryptoZip = new CryptoZip(zip, KEY, IV_KEY);
        cryptoZip.decompress(src, dest);
    }

    @Test
    public void test() {
        System.err.println(CryptoUtils.padding(IV_KEY, 16));
    }

}
