package org.warless.xender.framework;

import org.warless.xender.entity.Resource;
import org.warless.xender.framework.xml.Dataset;

import java.util.List;

/**
 * ExecutorConfig
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class ExecutorConfig {

    private String src;
    private String dest;
    private int maxSize;
    private String zipName;
    private String workspace;
    private String fileDir;
    private Constants.DatasetType datasetType;
    private List<String> pcbFileNames;
    private List<Resource> resourceList;
    private Dataset info;
    private Dataset struct;
    private String templatePath;
    private ClientPool clientPool;
    private String zipPath;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }

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

    public List<String> getPcbFileNames() {
        return pcbFileNames;
    }

    public void setPcbFileNames(List<String> pcbFileNames) {
        this.pcbFileNames = pcbFileNames;
    }

    public Constants.DatasetType getDatasetType() {
        return datasetType;
    }

    public void setDatasetType(Constants.DatasetType datasetType) {
        this.datasetType = datasetType;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public Dataset getInfo() {
        return info;
    }

    public void setInfo(Dataset info) {
        this.info = info;
    }

    public Dataset getStruct() {
        return struct;
    }

    public void setStruct(Dataset struct) {
        this.struct = struct;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public ClientPool getClientPool() {
        return clientPool;
    }

    public void setClientPool(ClientPool clientPool) {
        this.clientPool = clientPool;
    }

    public String getZipPath() {
        return zipPath;
    }

    public void setZipPath(String zipPath) {
        this.zipPath = zipPath;
    }
}
