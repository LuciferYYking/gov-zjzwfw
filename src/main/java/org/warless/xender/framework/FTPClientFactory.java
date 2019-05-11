package org.warless.xender.framework;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.warless.xender.framework.constant.ClientConfig;
import org.warless.xender.framework.ftp.FTP;

import java.io.IOException;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/10
 */
public class FTPClientFactory implements ClientFactory {

    public FTPClientFactory() { }

    @Override
    public Client createClient(ClientConfig config) {
        FTPClient ftpClient = new FTPClient();
        if (StringUtils.isNotEmpty(config.getEncoding())) {
            ftpClient.setControlEncoding(config.getEncoding());
        }
        try {
            ftpClient.connect(config.getHost(), config.getPort());
            ftpClient.login(config.getUsername(), config.getPassword());
            ftpClient.setFileType(config.getFileType());
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.abort();
                ftpClient.disconnect();
                return null;
            }
            return new FTP(ftpClient);
        } catch (IOException e) {
            if (ftpClient.isConnected()) {
                try { ftpClient.disconnect(); } catch (IOException ignored) {}
            }
        }
        return null;
    }

}
