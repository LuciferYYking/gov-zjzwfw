package org.warless.xender.framework.constant;

import java.util.List;

/**
 * Data
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/9
 */
public class Data {

    public static final String TAG_NAME = "DATA";

    private List<Item> items;
    private List<Dataset> datasets;

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

}
