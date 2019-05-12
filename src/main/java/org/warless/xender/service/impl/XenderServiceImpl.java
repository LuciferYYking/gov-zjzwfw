package org.warless.xender.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warless.xender.autoconfigure.FtpAutoConfiguration;
import org.warless.xender.autoconfigure.XenderAutoConfiguration;
import org.warless.xender.autoconfigure.XmlAutoConfiguration;
import org.warless.xender.entity.Resource;
import org.warless.xender.framework.*;
import org.warless.xender.framework.executors.FTPExecutor;
import org.warless.xender.framework.executors.PrepareExecutor;
import org.warless.xender.framework.executors.XmlExecutor;
import org.warless.xender.framework.executors.ZipExecutor;
import org.warless.xender.mapper.ResourceMapper;
import org.warless.xender.service.XenderService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * XenderServiceImpl
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
@Service
public class XenderServiceImpl implements XenderService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private FtpAutoConfiguration ftpAutoConfiguration;
    @Autowired
    private XmlAutoConfiguration xmlAutoConfiguration;
    @Autowired
    private XenderAutoConfiguration xenderAutoConfiguration;
    @Autowired
    private ClientPool clientPool;
    private XenderExecutorChain senderChain;
    private XenderExecutorChain receiverChain;

    @PostConstruct
    public void init() {
        senderChain = new XenderExecutorChain(new PrepareExecutor());
        senderChain.setNextChain(new XenderExecutorChain(new XmlExecutor())
                .setNextChain(new XenderExecutorChain(new ZipExecutor())
                        .setNextChain(new XenderExecutorChain(new FTPExecutor()))));
    }

    @Override
    public String upload(String fileName) {
        List<Resource> resourceList = resourceMapper.selectAll();
        ExecutorConfig config = new ExecutorConfig();
        config.setWorkspace(xenderAutoConfiguration.getWorkspace());
        config.setFileDir(xenderAutoConfiguration.getFileDir());
        config.setDatasetType(Constants.DatasetType.FILE);
        config.setResourceList(resourceList);
        config.setMaxSize(xmlAutoConfiguration.getPcbSize());
        config.setClientPool(clientPool);
        config.setTemplatePath(xmlAutoConfiguration.getTemplateLocation());
        config.setCryptoIvkey(xenderAutoConfiguration.getCryptoIvkey());
        config.setCryptoKey(xenderAutoConfiguration.getCryptoKey());
        senderChain.execute(config);
        return "Upload OK.";
    }

    @Override
    public String download(String fileName) {
        return "Download OK!";
    }

}
