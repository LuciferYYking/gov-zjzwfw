package org.warless.xender.test.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.Iterator;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/9
 */
public class XmlReaderTest {

    @Test
    public void read() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src\\test\\resources\\GAB_ZIP_INDEX.xml");
        Element root = document.getRootElement();
        for (Iterator i = root.elementIterator(); i.hasNext();) {
            Element element = (Element) i.next();
            System.err.println(element.getName());
        }
    }

}
