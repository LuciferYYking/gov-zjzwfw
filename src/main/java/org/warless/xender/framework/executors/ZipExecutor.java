package org.warless.xender.framework.executors;

import org.warless.xender.framework.CryptoZip;
import org.warless.xender.framework.Executor;
import org.warless.xender.framework.ExecutorChain;
import org.warless.xender.framework.ExecutorConfig;
import org.warless.xender.framework.zip.Zip;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * ZipExecutor
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class ZipExecutor implements Executor {

    static String ZIP_NAME = "45646546.zip";

    @Override
    public void execute(ExecutorConfig config, ExecutorChain chain) {
        String src = config.getWorkspace();
        String dest = config.getWorkspace() + File.separator + ZIP_NAME;
        Zip zip = new Zip(StandardCharsets.UTF_8);
        CryptoZip cryptoZip = new CryptoZip(zip, "1234567890123456", "1234567891234560");
        try {
            cryptoZip.compress(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setZipName(ZIP_NAME);
        config.setZipPath(dest);
        chain.execute(config);
    }
}
