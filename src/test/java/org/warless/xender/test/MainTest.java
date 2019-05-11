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
import org.springframework.util.ResourceUtils;
import org.warless.xender.XenderApplication;
import org.warless.xender.autoconfigure.XmlAutoConfiguration;
import org.warless.xender.entity.Resource;
import org.warless.xender.framework.Client;
import org.warless.xender.framework.xml.*;
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
        Data DATA = new Data();

        int index = 0;
        Data bcpData = new Data();
        bcpData.setItems(new ArrayList<Item>(){{
            add(new Item("WY0001", "type", "类型"));
            add(new Item("WY0002", "value", "内容"));
            add(new Item("WY0003", "path", "文件路径"));
        }});
        Dataset dataset = new Dataset("WA_COMMON_010014", "BCP数据文件信息");
        dataset.addData(bcpData);

        String[] bcpFiles = new String[n - 1];

        List<Data> dataList = new ArrayList<>();

        for (int i = 0; i < n - 1; ++i) {
            String fileName = name + i + ".bcp";
            String path = WORKSPACE + File.separator + fileName;
            OutputStream out = new FileOutputStream(path);
            clients.subList(i*PAGE_SIZE, i*PAGE_SIZE + PAGE_SIZE).forEach(res -> {
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
            bcpFiles[i] = fileName;
            Data data = new Data();
            data.setItems(new ArrayList<Item>() {{
                Item item0 = new Item();
                item0.setKey(Constants.XmlEntryEnum.BCP_FILE_PATH.getKey())
                        .setValue("./")
                        .setRemark(Constants.XmlEntryEnum.BCP_FILE_PATH.getRemark());
                add(item0);

                Item item1 = new Item();
                item1.setKey(Constants.XmlEntryEnum.BCP_FILE_NAME.getKey())
                        .setValue(fileName)
                        .setRemark(Constants.XmlEntryEnum.BCP_FILE_NAME.getRemark());
                add(item1);

                Item item2 = new Item();
                item2.setKey(Constants.XmlEntryEnum.LINE_COUNT.getKey())
                        .setValue(PAGE_SIZE + "")
                        .setRemark(Constants.XmlEntryEnum.LINE_COUNT.getRemark());
                add(item2);
            }});
            dataList.add(data);
        }
        Dataset dataset1 = new Dataset("WA_COMMON_010014", "BCP数据文件信息");
        dataset1.setDatas(dataList);

        DATA.addDataset(dataset);
        DATA.addDataset(dataset1);

        Item item0 = new Item();
        item0.setKey(Constants.XmlEntryEnum.TAB.getKey())
                .setValue(Constants.XmlEntryEnum.TAB.getDefaultValue())
                .setRemark(Constants.XmlEntryEnum.TAB.getRemark());
        DATA.addItem(item0);

        Dataset dataset2 = new Dataset("WA_COMMON_010013", "BCP文件描述信息");
        dataset2.addData(DATA);


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

    @Test
    public void one() throws IOException {
        Message message = XmlWriter.getTemplate("classpath:GAB_ZIP_INDEX.xml");
        Data data = message.getDatasets().get(0).getDatas().get(0).getDatasets().get(0).getDatas().get(0);
        Dataset dataset = new Dataset("WA_COMMON_010014", "BCP数据文件信息");
        Data dt = new Data();
        dt.setItems(new ArrayList<Item>(){{
            Item item1 = new Item(Constants.XmlEntryEnum.BCP_FILE_PATH.getKey(),
                    Constants.XmlEntryEnum.BCP_FILE_PATH.getDefaultValue(),
                    Constants.XmlEntryEnum.BCP_FILE_PATH.getRemark());
            Item item2 = new Item(Constants.XmlEntryEnum.BCP_FILE_NAME.getKey(),
                    "000-564654-26524-01.bcp",
                    Constants.XmlEntryEnum.BCP_FILE_NAME.getRemark());
            Item item3 = new Item(Constants.XmlEntryEnum.LINE_COUNT.getKey(),
                    xmlAutoConfiguration.getPcbSize() + "",
                    Constants.XmlEntryEnum.LINE_COUNT.getRemark());
            add(item1);
            add(item2);
            add(item3);
        }});
        dataset.addData(dt);

        Dataset dataset1 = new Dataset("WA_COMMON_010015", "BCP文件数据结构");
        Data dt1 = new Data();
        dt1.setItems(new ArrayList<Item>(){{
            add(new Item("WY0001", "type", "类型"));
            add(new Item("WY0002", "value", "内容"));
            add(new Item("WY0003", "path", "文件路径"));
        }});
        dataset1.addData(dt1);

        data.addDataset(dataset);
        data.addDataset(dataset1);
        Document document = DocumentHelper.createDocument();
        document.add(message.transfer());
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter("F:\\foo.xml"), format);
        writer.write(document);
        writer.close();
        System.err.println(message);
    }

    @Test
    public void two() throws FileNotFoundException {
        Message message = XmlWriter.getTemplate("classpath:GAB_ZIP_INDEX.xml");
        Message a = new Message();
        BeanUtils.copyProperties(message, a);
        System.err.println(message);
    }

}
