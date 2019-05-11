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
import org.warless.xender.framework.Client;
import org.warless.xender.framework.ClientPool;
import org.warless.xender.framework.CryptoZip;
import org.warless.xender.framework.xml.*;
import org.warless.xender.framework.zip.Zip;
import org.warless.xender.mapper.ResourceMapper;
import org.warless.xender.service.XenderService;
import org.warless.xender.utils.CommonUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    @Override
    public String upload(String fileName) {
        List<Resource> resourceList = resourceMapper.selectAll();
        int pageSize = xmlAutoConfiguration.getPcbSize();
        int page = (resourceList.size()) / pageSize;
        int mod = resourceList.size() % pageSize;
        if ( mod != 0) {
            ++page;
        }
        String fileDir = xenderAutoConfiguration.getWorkspace() + File.separator + xenderAutoConfiguration.getFileDir();
        File file = new File(fileDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        Dataset struct = new Dataset("WA_COMMON_010015", "BCP文件数据结构");
        Data bcpData = new Data();
        bcpData.setItems(new ArrayList<Item>(){{
            add(new Item("WY0001", "type", "类型"));
            add(new Item("WY0002", "value", "内容"));
            add(new Item("WY0003", "path", "文件路径"));
        }});
        struct.addData(bcpData);
        Dataset info = new Dataset("WA_COMMON_010014", "BCP数据文件信息");

        for (int i = 0; i < page ; ++i) {
            int begin = i * pageSize;
            int end = i == page - 1 ? begin + mod : begin + pageSize;
            List<Resource> subList = resourceList.subList(begin, end);
            String pcbName = fileName + "-" + i +".bcp";
            String path = xenderAutoConfiguration.getWorkspace() + File.separator + pcbName;
            try {
                OutputStream out = new FileOutputStream(path);
                subList.forEach(res -> {
                    String line = res.getType() + "\t" + res.getContent() + "\t" + res.getPath() + "\n";
                    try {
                        out.write(line.getBytes(StandardCharsets.UTF_8));
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CommonUtils.copy(res.getPath(), fileDir);
                });
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Data data = new Data();
            data.setItems(new ArrayList<Item>() {{
                Item item0 = new Item();
                item0.setKey(Constants.XmlEntryEnum.BCP_FILE_PATH.getKey())
                        .setValue("./")
                        .setRemark(Constants.XmlEntryEnum.BCP_FILE_PATH.getRemark());
                add(item0);

                Item item1 = new Item();
                item1.setKey(Constants.XmlEntryEnum.BCP_FILE_NAME.getKey())
                        .setValue(pcbName)
                        .setRemark(Constants.XmlEntryEnum.BCP_FILE_NAME.getRemark());
                add(item1);

                Item item2 = new Item();
                item2.setKey(Constants.XmlEntryEnum.LINE_COUNT.getKey())
                        .setValue((end - begin) + "")
                        .setRemark(Constants.XmlEntryEnum.LINE_COUNT.getRemark());
                add(item2);
            }});
            info.addData(data);
        }
        try {
            Message message = XmlWriter.getTemplate(xmlAutoConfiguration.getTemplateLocation());
            Data data = message.getDatasets().get(0).getDatas().get(0).getDatasets().get(0).getDatas().get(0);
            data.addDataset(info);
            data.addDataset(struct);
            Document document = DocumentHelper.createDocument();
            document.add(message.transfer());
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileWriter(xenderAutoConfiguration.getWorkspace() + File.separator + "GAB_ZIP_INDEX.xml"), format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String src = xenderAutoConfiguration.getWorkspace();
        String dest = xenderAutoConfiguration.getWorkspace() + File.separator + "45646546.zip";
        Zip zip = new Zip(StandardCharsets.UTF_8);
        CryptoZip cryptoZip = new CryptoZip(zip, "1234567890123456", "1234567891234560");
        try {
            cryptoZip.compress(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Client ftp = clientPool.getClient();
        ftp.upload(dest, "\\45646546.zip");
        return "Upload OK.";
    }

    @Override
    public String download(String fileName) {
        return "Download OK!";
    }

}
