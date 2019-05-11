package org.warless.xender.framework.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
        Document document = null;
        try {
            document = saxReader.read(path);
            document.setXMLEncoding(CHARSET);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public Element getRootElement(Document doc) {
        return doc.getRootElement();
    }



}
