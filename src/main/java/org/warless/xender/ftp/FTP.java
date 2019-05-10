package org.warless.xender.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

/**
 * FTP
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/8
 */
public class FTP implements Client {

    private FTPClient ftpClient;

    public FTP(FTPClient client) {
        this.ftpClient = client;
    }

    @Override
    public void upload(String src, String dest) {
        try {
            InputStream input = new FileInputStream(src);
            ftpClient.storeFile(dest, input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void download(String src, String dest) {
        try {
            OutputStream output = new FileOutputStream(dest);
            ftpClient.retrieveFile(src, output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            ftpClient.noop();
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                 ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
