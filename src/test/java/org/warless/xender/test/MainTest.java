package org.warless.xender.test;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.warless.xender.XenderApplication;
import org.warless.xender.constant.ClientConfig;
import org.warless.xender.ftp.Client;
import org.warless.xender.ftp.ClientFactory;
import org.warless.xender.ftp.ClientPool;
import org.warless.xender.ftp.FTPClientFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XenderApplication.class)
public class MainTest {

    static List<Client> clients = new ArrayList<>();

    @Autowired
    private ClientPool clientPool;

    @Before
    public void before() {
//        ClientFactory factory = new FTPClientFactory();
//        ClientConfig config = new ClientConfig();
//        config.setHost("192.168.1.102").setPort(21).setUsername("Anonymous").setPassword("").setFileType(FTPClient.BINARY_FILE_TYPE);
//        clients.add(factory.createClient(config));
    }

    @Test
    public void t() throws IOException, InterruptedException {
        String path = "E:\\workspaces\\workspace_java\\Xender\\src\\main\\resources\\application.yml";
//        Client ftp = clients.get(0);
//        ftp.upload(path, "\\app.yml");
//        clients.forEach(Client::close);
        Client ftp = clientPool.getClient();
        ftp.upload(path, "\\app.yml");
        clientPool.releaseClient(ftp);

    }


}
