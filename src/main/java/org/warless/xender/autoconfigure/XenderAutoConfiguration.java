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
@ConfigurationProperties(ignoreUnknownFields = true, prefix = "xender")
public class XenderAutoConfiguration {

    private String workspace;
    private String fileDir;

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }
}
