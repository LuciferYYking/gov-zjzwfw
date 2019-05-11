package org.warless.xender.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Xender
 *
 * @author : yubb
 * @version : 1.0.0
 * @date : 2019/5/8
 */
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "xender.xml")
public class XmlAutoConfiguration {

    private String templateLocation;
    private int pcbSize;
    private int pcbCount;
    private String encoding;

    public String getTemplateLocation() {
        return templateLocation;
    }

    public void setTemplateLocation(String templateLocation) {
        this.templateLocation = templateLocation;
    }

    public int getPcbSize() {
        return pcbSize;
    }

    public void setPcbSize(int pcbSize) {
        this.pcbSize = pcbSize;
    }

    public int getPcbCount() {
        return pcbCount;
    }

    public void setPcbCount(int pcbCount) {
        this.pcbCount = pcbCount;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
