package org.warless.xender.framework.constant;

import java.util.List;

/**
 * Dataset
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/9
 */
public class Dataset {

    public static final String TAG_NAME = "DATASET";
    public static final String NAME_KEY = "name";
    public static final String REMARK_KEY = "rmk";

    private String name;
    private String remark;
    private List<Data> datas;

    public String getName() {
        return name;
    }

    public Dataset setName(String name) {
        this.name = name;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Dataset setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public Dataset setDatas(List<Data> datas) {
        this.datas = datas;
        return this;
    }

}
