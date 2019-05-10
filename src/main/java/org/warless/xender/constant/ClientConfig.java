package org.warless.xender.constant;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/10
 */
public class ClientConfig {

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

    public ClientConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public ClientConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ClientConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ClientConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEncoding() {
        return encoding;
    }

    public ClientConfig setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public int getFileType() {
        return fileType;
    }

    public ClientConfig setFileType(int fileType) {
        this.fileType = fileType;
        return this;
    }

    public boolean isUseProtocol() {
        return useProtocol;
    }

    public void setUseProtocol(boolean useProtocol) {
        this.useProtocol = useProtocol;
    }

}
