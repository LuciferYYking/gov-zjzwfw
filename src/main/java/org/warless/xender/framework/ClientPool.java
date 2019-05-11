package org.warless.xender.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warless.xender.autoconfigure.FtpAutoConfiguration;
import org.warless.xender.framework.ftp.ClientConfig;

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

    private int size;
    private ClientFactory clientFactory;

    public ClientPool() {
        this(5);
    }

    public ClientPool(int size) {
        this.size = size;
        this.clientFactory = new FTPClientFactory();
        CLIENTS = new ArrayList<>(size);
    }

    @PostConstruct
    public void init() {
        ClientConfig config = new ClientConfig();
        config.setHost(ftpAutoConfiguration.getHost())
                .setPort(ftpAutoConfiguration.getPort())
                .setUsername(ftpAutoConfiguration.getUsername())
                .setPassword(ftpAutoConfiguration.getPassword())
                .setFileType(ftpAutoConfiguration.getFileType());
        for (int i = 0; i < size; ++i) {
            CLIENTS.add(clientFactory.createClient(config));
        }
    }

    public synchronized Client getClient() {
        while (CLIENTS.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return CLIENTS.remove(0);
    }

    public synchronized void releaseClient(Client client) {
        CLIENTS.add(client);
        this.notify();
    }

    @PreDestroy
    public void close() {
        CLIENTS.forEach(Client::freeClient);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
