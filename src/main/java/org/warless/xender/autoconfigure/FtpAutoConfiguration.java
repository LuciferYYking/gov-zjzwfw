package org.warless.xender.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Xender
 *
 * @author : yubb
 * @version : 1.0.0
 * @date : 2019/5/8
 */
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "xender.ftp")
public class FtpAutoConfiguration {

    private String host;
    private int port;
    private String username;
    private String password;
    private String encoding;
    private int fileType;
    private boolean useProtocol;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public boolean isUseProtocol() {
        return useProtocol;
    }

    public void setUseProtocol(boolean useProtocol) {
        this.useProtocol = useProtocol;
    }
}
