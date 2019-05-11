package org.warless.xender.framework.xml;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.warless.xender.framework.XmlElement;
import org.warless.xender.utils.CommonUtils;

import java.util.List;

/**
 * Dataset
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/9
 */
public class Dataset implements XmlElement {

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

    @Override
    public Element transfer() {
        Element root = DocumentHelper.createElement(TAG_NAME);
        root.addAttribute(NAME_KEY, name);
        root.addAttribute(REMARK_KEY, remark);
        if (CommonUtils.isNotEmpty(datas)) {
            datas.forEach(data -> root.add(data.transfer()));
        }
        return root;
    }

}
