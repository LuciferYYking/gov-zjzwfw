package org.warless.xender.framework.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;
import org.warless.xender.framework.XmlElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * XmlReader
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/8
 */
public class XmlReader {

    private static final String CHARSET = "UTF-8";

    private String path;
    private SAXReader saxReader;

    public XmlReader(String path) {
        this.path = path;
        this.saxReader = new SAXReader();
    }

    public Document loadDocument() {
        return loadDocument(new File(path));
    }

    public Document loadDocument(File file) {
        Document document = null;
        try {
            document = saxReader.read(file);
            document.setXMLEncoding(CHARSET);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public Element getRootElement(Document doc) {
        return doc.getRootElement();
    }



    public void parse(Element node, XmlElement xmlElement) {
        if (node == null || node.elements().size() < 1) {
            return;
        }
        for (Iterator<Element> it = node.elementIterator(); it.hasNext();) {
            Element element = it.next();
            if (xmlElement instanceof Message) {
                Message message = (Message) xmlElement;
                Dataset dataset = new Dataset();
                dataset.setName(element.attributeValue(Dataset.NAME_KEY));
                dataset.setVersion(element.attributeValue(Dataset.VERSION_KEY));
                dataset.setRemark(element.attributeValue(Dataset.REMARK_KEY));
                message.addDataset(dataset);
                parse(element, dataset);
            } else if (Dataset.TAG_NAME.equals(element.getName())) {
                Data data = (Data) xmlElement;
                Dataset dataset = new Dataset();
                dataset.setName(element.attributeValue(Dataset.NAME_KEY))
                        .setRemark(element.attributeValue(Dataset.REMARK_KEY));
                data.addDataset(dataset);
                parse(element, dataset);
            } else if (Data.TAG_NAME.equals(element.getName())) {
                Dataset dataset = (Dataset) xmlElement;
                Data data = new Data();
                dataset.addData(data);
                parse(element, data);
            } else if (Item.TAG_NAME.equals(element.getName())) {
                Data data = (Data) xmlElement;
                Item item = new Item();
                item.setKey(element.attributeValue(Item.KEY_KEY))
                        .setValue(element.attributeValue(Item.VALUE_KEY))
                        .setRemark(element.attributeValue(Item.REMARK_KEY));
                data.addItem(item);
            }
        }
    }

}
