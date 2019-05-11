package org.warless.xender.framework.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
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

    private static Message template = null;

    public static Message getTemplate(String path) throws FileNotFoundException {
        if (template == null) {
            XmlReader reader = new XmlReader("");
            File file = path.startsWith("classpath") ? ResourceUtils.getFile(path) : new File(path);
            Document document = reader.loadDocument(file);
            Element root = reader.getRootElement(document);
            template = new Message();
            reader.parse(root, template);
        }
        Message message = new Message();
        BeanUtils.copyProperties(template, message);
        return message;
    }

    public static void createXml() throws IOException {

    }


    public Message createMessage() {
        Message message = new Message();
        return message;
    }




}
