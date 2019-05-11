package org.warless.xender.framework.xml;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.warless.xender.framework.XmlElement;
import org.warless.xender.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Message
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class Message implements XmlElement {

    public static final String TAG_NAME = "MESSAGE";

    private List<Dataset> datasets = new ArrayList<>();

    public Message addDataset(Dataset dataset) {
        this.datasets.add(dataset);
        return this;
    }

    public List<Dataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

    @Override
    public Element transfer() {
        Element root = DocumentHelper.createElement(TAG_NAME);
        if (CommonUtils.isNotEmpty(datasets)) {
            datasets.forEach(dataset -> root.add(dataset.transfer()));
        }
        return root;
    }

}
