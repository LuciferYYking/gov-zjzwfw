package org.warless.xender.framework.xml;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.warless.xender.framework.XmlElement;
import org.warless.xender.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Data
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/9
 */
public class Data implements XmlElement {

    public static final String TAG_NAME = "DATA";

    private List<Item> items = new ArrayList<>();
    private List<Dataset> datasets = new ArrayList<>(4);

    public Data addItem(Item item) {
        this.items.add(item);
        return this;
    }

    public Data addDataset(Dataset dataset) {
        this.datasets.add(dataset);
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public Data setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public List<Dataset> getDatasets() {
        return datasets;
    }

    public Data setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
        return this;
    }

    @Override
    public Element transfer() {
        Element root = DocumentHelper.createElement(TAG_NAME);
        if (CommonUtils.isNotEmpty(items)) {
            items.forEach(item -> root.add(item.transfer()));
        }
        if (CommonUtils.isNotEmpty(datasets)) {
            datasets.forEach(dataset -> root.add(dataset.transfer()));
        }
        return root;
    }

}
