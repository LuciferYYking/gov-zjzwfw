package org.warless.xender.framework.xml;

import java.util.List;

/**
 * XmlWriterConfig
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class XmlWriterConfig {

    private String datasetCode;
    private String orgCode;
    List<Item> dataInfoItems;
    List<Item> dataStructItems;


    public String getDatasetCode() {
        return datasetCode;
    }

    public void setDatasetCode(String datasetCode) {
        this.datasetCode = datasetCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public List<Item> getDataInfoItems() {
        return dataInfoItems;
    }

    public void setDataInfoItems(List<Item> dataInfoItems) {
        this.dataInfoItems = dataInfoItems;
    }

    public List<Item> getDataStructItems() {
        return dataStructItems;
    }

    public void setDataStructItems(List<Item> dataStructItems) {
        this.dataStructItems = dataStructItems;
    }
}
