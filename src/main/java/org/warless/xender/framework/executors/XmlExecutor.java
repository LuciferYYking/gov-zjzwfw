package org.warless.xender.framework.executors;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.warless.xender.framework.Executor;
import org.warless.xender.framework.ExecutorChain;
import org.warless.xender.framework.ExecutorConfig;
import org.warless.xender.framework.xml.Data;
import org.warless.xender.framework.xml.Message;
import org.warless.xender.framework.xml.XmlWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * XmlExecutor
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class XmlExecutor implements Executor {
    @Override
    public void execute(ExecutorConfig config, ExecutorChain chain) {
        try {
            Message message = XmlWriter.getTemplate(config.getTemplatePath());
            Data data = message.getDatasets().get(0).getDatas().get(0).getDatasets().get(0).getDatas().get(0);
            data.addDataset(config.getInfo());
            data.addDataset(config.getStruct());
            Document document = DocumentHelper.createDocument();
            document.add(message.transfer());
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileWriter(config.getWorkspace() + File.separator + "GAB_ZIP_INDEX.xml"), format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        chain.execute(config);
    }
}
