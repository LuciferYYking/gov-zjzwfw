package org.warless.xender.test.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.warless.xender.framework.xml.Data;
import org.warless.xender.framework.xml.Dataset;
import org.warless.xender.framework.xml.Item;
import org.warless.xender.framework.xml.XmlWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/9
 */
public class XmlReaderTest {

    private static final String PATH = "src\\test\\resources\\GAB_ZIP_INDEX.xml";


    @Test
    public void read() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(PATH);
        Element root = document.getRootElement();
        Data data = new Data();
        test(root, data);

        System.err.println(1);
//        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
//            Element element =  it.next();
//            if (!Item.TAB_NAME.equals(element.getName()) && element.elements().size() > 0) {
//                test(element);
//            }
//        }
    }

    public void test(Element node, Object res) {
        if (node == null || node.elements().size() < 1) {
            return;
        }
        for (Iterator<Element> it = node.elementIterator(); it.hasNext();) {
            Element element = it.next();
            if (Item.TAG_NAME.equals(element.getName())) {
                Data data = (Data) res;
                Item item = new Item();
                item.setKey(element.attributeValue(Item.KEY_KEY))
                        .setValue(element.attributeValue(Item.VALUE_KEY))
                        .setRemark(element.attributeValue(Item.REMARK_KEY));
                List<Item> itemList = data.getItems() == null ? new ArrayList<>() : data.getItems();
                itemList.add(item);
                data.setItems(itemList);
                test(element, res);
            }
            if (Data.TAG_NAME.equals(element.getName())) {
                Data data = new Data();
                Dataset dataset = (Dataset) res;
                List<Data> dataList = dataset.getDatas() == null ? new ArrayList<>() : dataset.getDatas();
                dataList.add(data);
                dataset.setDatas(dataList);
                test(element, data);
            }
            if (Dataset.TAG_NAME.equals(element.getName())) {
                Data data = (Data) res;
                Dataset dataset = new Dataset();
                dataset.setName(element.attributeValue(Dataset.NAME_KEY))
                        .setRemark(element.attributeValue(Dataset.REMARK_KEY));
                List<Dataset> datasetList = data.getDatasets() == null ?
                        new ArrayList<>() : data.getDatasets();
                datasetList.add(dataset);
                data.setDatasets(datasetList);
                test(element, dataset);
            }
        }
    }

    @Test
    public void write() throws IOException {
        XmlWriter.createXml();
    }

}
