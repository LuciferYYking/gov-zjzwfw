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

    SnowflakeWorker worker = new SnowflakeWorker(1, 1);

    @Autowired
    private ResourceMapper resourceMapper;

    @Before
    public void before() {

    }

    @Test
    public void db() {
        Resource res = new Resource();
        res.setId(worker.nextId());
        res.setType("message");
        res.setContent("image");
        for (int i = 0; i < 100; ++i) {
            res.setId(worker.nextId());
            res.setPath("D://files/" + i +".jpg");
            resourceMapper.insert(res);
        }
        List<Resource> resourceList = resourceMapper.selectAll();
        resourceList.forEach(System.err::println);
    }



}
