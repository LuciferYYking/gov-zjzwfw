package org.warless.xender.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warless.xender.autoconfigure.FtpAutoConfiguration;
import org.warless.xender.constant.ClientConfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/10
 */
@Component
public class ClientPool {

    private static List<Client> CLIENTS = null;

    @Autowired
    private FtpAutoConfiguration ftpAutoConfiguration;

    private int initSize;
    private int maxSize;
    private ClientFactory clientFactory;

    public ClientPool() {
        this(8, 16);
    }

    public ClientPool(int initSize, int maxSize) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.clientFactory = new FTPClientFactory();
        CLIENTS = new ArrayList<>(initSize);
    }

    @PostConstruct
    public void init() {
        ClientConfig config = new ClientConfig();
        config.setHost(ftpAutoConfiguration.getHost()).setPort(21).setUsername("Anonymous").setPassword("").setFileType(FTPClient.BINARY_FILE_TYPE);
        for (int i = 0; i < initSize; ++i) {
            CLIENTS.add(clientFactory.createClient(config));
        }
    }

    public Client getClient() {
        return null;
    }

    @PreDestroy
    public void close() {
        CLIENTS.forEach(Client::close);
    }

    public int getInitSize() {
        return initSize;
    }

    public void setInitSize(int initSize) {
        this.initSize = initSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
