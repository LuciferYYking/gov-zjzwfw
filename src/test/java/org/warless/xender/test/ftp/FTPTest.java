package org.warless.xender.test.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.warless.xender.framework.constant.ClientConfig;
import org.warless.xender.framework.Client;
import org.warless.xender.framework.ClientFactory;
import org.warless.xender.framework.FTPClientFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FTPTest
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class FTPTest {

    @Test
    public void t() throws IOException, InterruptedException {
        List<Client> clients = new ArrayList<>();
        ClientFactory factory = new FTPClientFactory();
        ClientConfig config = new ClientConfig();
        config.setHost("192.168.1.102").setPort(21).setUsername("Anonymous").setPassword("").setFileType(FTPClient.BINARY_FILE_TYPE);
        clients.add(factory.createClient(config));
        String path = "E:\\workspaces\\workspace_java\\Xender\\src\\main\\resources\\application.yml";
        Client ftp = clients.get(0);
        ftp.upload(path, "\\app.yml");
        clients.forEach(Client::freeClient);
    }

}
