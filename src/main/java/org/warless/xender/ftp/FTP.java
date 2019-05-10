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

    private boolean alive;
    private FTPClient ftpClient;

    public FTP(FTPClient client) {
        this.ftpClient = client;
    }

    @Override
    public void upload(String src, String dest) {
        this.alive = true;
        try {
            InputStream input = new FileInputStream(src);
            ftpClient.storeFile(dest, input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.alive = false;
        }
    }

    @Override
    public void download(String src, String dest) {
        this.alive = true;
        try {
            OutputStream output = new FileOutputStream(dest);
            ftpClient.retrieveFile(src, output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.alive = false;
        }
    }

    @Override
    public void freeClient() {
        try {
            ftpClient.noop();
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                 ftpClient.disconnect();
            }
            System.err.println("Closed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

}
