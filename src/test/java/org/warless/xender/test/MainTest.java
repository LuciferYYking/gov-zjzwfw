package org.warless.xender.test;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.warless.xender.XenderApplication;
import org.warless.xender.autoconfigure.XmlAutoConfiguration;
import org.warless.xender.entity.Resource;
import org.warless.xender.framework.Client;
import org.warless.xender.framework.ClientPool;
import org.warless.xender.framework.Constants;
import org.warless.xender.framework.CryptoZip;
import org.warless.xender.framework.ftp.FTP;
import org.warless.xender.framework.xml.*;
import org.warless.xender.framework.zip.Zip;
import org.warless.xender.mapper.ResourceMapper;
import org.warless.xender.utils.SnowflakeWorker;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    static List<Resource> clients;
    private static final int PAGE_SIZE = 10;
    private static final String WORKSPACE = "F:\\Xender\\temp";
    SnowflakeWorker worker = new SnowflakeWorker(1, 1);

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private XmlAutoConfiguration xmlAutoConfiguration;
    @Autowired
    private ClientPool clientPool;

    @Before
    public void before() {
        clients = resourceMapper.selectAll();
    }

    @Test
    public void one() {
        Resource res = new Resource();
        res.setType("message");
        for (int i = 0; i < 100; ++i) {
            res.setPath("F:\\Xender\\files" + File.separator + i + ".png" );
            res.setId(worker.nextId());
            resourceMapper.insert(res);
        }
    }

    @Test
    public void step_1() {
        String remotePath = "\\45646546.zip";
        Client ftp = clientPool.getClient();
        File file = new File(remotePath);
        ftp.download(remotePath, WORKSPACE + File.separator + file.getName());
    }

    @Test
    public void step_2() throws IOException {
        String zipPath = WORKSPACE + File.separator + "45646546.zip";
        Zip zip = new Zip(StandardCharsets.UTF_8);
        CryptoZip cryptoZip = new CryptoZip(zip, "1234567890123456", "1234567891234560");
        cryptoZip.decompress(zipPath, WORKSPACE);
    }

    @Test
    public void step_3() {


    }

}
