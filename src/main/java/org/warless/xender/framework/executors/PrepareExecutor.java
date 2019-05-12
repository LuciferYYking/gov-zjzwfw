package org.warless.xender.framework.executors;

import org.warless.xender.entity.Resource;
import org.warless.xender.framework.Constants;
import org.warless.xender.framework.Executor;
import org.warless.xender.framework.ExecutorChain;
import org.warless.xender.framework.ExecutorConfig;
import org.warless.xender.framework.xml.Data;
import org.warless.xender.framework.xml.Dataset;
import org.warless.xender.framework.xml.Item;
import org.warless.xender.utils.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * PrepareExecutor
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/12
 */
public class PrepareExecutor implements Executor {

    private static final String BCP_FILE_SUFFIX = ".bcp";

    static final String BCP_FILE_NAME = "000-000000-1475240221-00000-WY_WEB_01-";

    @Override
    public void execute(ExecutorConfig config, ExecutorChain chain) {
        String fileDir = config.getWorkspace() + File.separator + config.getFileDir();
        File file = new File(fileDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        Dataset struct = createDataset(config.getDatasetType());
        int pageSize = config.getMaxSize();
        int page = (config.getResourceList().size()) / pageSize;
        int mod = config.getResourceList().size() % pageSize;
        if ( mod != 0) {
            ++page;
        }
        Dataset info = new Dataset("WA_COMMON_010014", "BCP数据文件信息");
        for (int i = 0; i < page ; ++i) {
            int begin = i * pageSize;
            int end = i == page - 1 ? begin + mod : begin + pageSize;
            List<Resource> subList = config.getResourceList().subList(begin, end);
            String pcbName = BCP_FILE_NAME + i + BCP_FILE_SUFFIX;;
            String filePath = config.getWorkspace() + File.separator + pcbName;
            writeBCPFile(filePath, subList, fileDir);
            Data data = createData(pcbName, end - begin);
            info.addData(data);
        }
        config.setStruct(struct);
        config.setInfo(info);
        chain.execute(config);
    }

    private void writeBCPFile(String path, List<Resource> subList, String fileDir) {
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
    }

    private Data createData(String pcbName, int size) {
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
                    .setValue(size + "")
                    .setRemark(Constants.XmlEntryEnum.LINE_COUNT.getRemark());
            add(item2);
        }});
        return data;
    }

    private Dataset createDataset(Constants.DatasetType type) {
        Dataset struct = new Dataset("WA_COMMON_010015", "BCP文件数据结构");
        Data bcpData = new Data();
        switch (type) {
            case LOG:
                break;
            case FILE:
                bcpData.setItems(new ArrayList<Item>(){{
                    add(new Item("WY0001", "type", "类型"));
                    add(new Item("WY0002", "value", "内容"));
                    add(new Item("WY0003", "path", "文件路径"));
                }});
                break;
            case INFO:
                break;
            default:
                break;
        }
        struct.addData(bcpData);
        return struct;
    }

}
