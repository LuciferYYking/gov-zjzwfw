package org.warless.xender.framework.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * XmlWriter
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/8
 */
public class XmlWriter {

    public static void createXml() throws IOException {
        Document document = DocumentHelper.createDocument();
        Message message = new Message();
        Data pcb = new Data();
        pcb.setItems(new ArrayList<Item>() { {
            add(new Item()
                    .setKey(XmlEntryEnum.BCP_FILE_PATH.getKey())
                    .setValue(XmlEntryEnum.BCP_FILE_PATH.getDefaultValue())
                    .setRemark(XmlEntryEnum.BCP_FILE_PATH.getRemark()));
            add(new Item()
                    .setKey(XmlEntryEnum.BCP_FILE_NAME.getKey())
                    .setValue(XmlEntryEnum.BCP_FILE_NAME.getDefaultValue())
                    .setRemark(XmlEntryEnum.BCP_FILE_NAME.getRemark()));
        }});
        Dataset dataset = new Dataset();
        dataset.setDatas(new ArrayList<Data>(){{
            add(pcb);
        }});
        message.setDatasets(new ArrayList<Dataset>(){{
            add(dataset);
        }} );
        document.add(message.transfer());
        FileWriter out = new FileWriter("foo.xml");
        document.write(out);
        out.close();
    }




}
