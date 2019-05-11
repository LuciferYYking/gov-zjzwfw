package org.warless.xender.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.warless.xender.XenderApplication;
import org.warless.xender.entity.Resource;
import org.warless.xender.framework.Client;
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

    @Before
    public void before() {
        clients = resourceMapper.selectAll();
    }



    @Test
    public void db() throws IOException {
        int n = (clients.size()) / PAGE_SIZE;
        if (clients.size() % PAGE_SIZE != 0) {
            ++n;
        }
        String dir = "file";
        String name = "000-023154-54564-1231-";
        String root = WORKSPACE + File.separator + dir;
        File file = new File(root);
        if (!file.exists()) {
            file.mkdirs();
        }
        int index = 0;
        for (int i = 0; i < n - 1; ++i) {
            String fileName = name + i + ".bcp";
            String path = WORKSPACE + File.separator + fileName;
            OutputStream out = new FileOutputStream(path);
            clients.subList(i*PAGE_SIZE, i*PAGE_SIZE+ PAGE_SIZE).forEach(res -> {
                String line = res.getType() + "\t" + res.getContent() + "\t" + res.getPath() + "\n";
                try {
                    out.write(line.getBytes(StandardCharsets.UTF_8));
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                copy(res.getPath(), WORKSPACE + File.separator + dir);
            });
            out.close();
        }



    }

    public void copy(String src, String dest) {
        File file = new File(src);
        System.err.println(file.getName());
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dest + File.separator + file.getName());
            byte[] buffer = new byte[2048];
            int len;
            while ((len =in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignored) {}
        }
    }

    @Test
    public void data() {
        Resource res = new Resource();
        res.setType("message");
        for (int i = 0; i < 100; ++i) {
            res.setPath("F:\\Xender\\files" + File.separator + i + ".png" );
            res.setId(worker.nextId());
            resourceMapper.insert(res);
        }
    }


}
