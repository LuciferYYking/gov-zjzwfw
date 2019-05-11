package org.warless.xender.framework.xml;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.warless.xender.framework.XmlElement;
import org.warless.xender.utils.CommonUtils;

import java.util.ArrayList;
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
    public static final String VERSION_KEY = "ver";

    private String name;
    private String remark;
    private String version;
    private List<Data> datas = new ArrayList<>(4);

    public Dataset() {
    }

    public Dataset(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public Dataset addData(Data data) {
        this.datas.add(data);
        return this;
    }

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

    public String getVersion() {
        return version;
    }

    public Dataset setVersion(String version) {
        this.version = version;
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
        if (StringUtils.isNotBlank(version)) {
            root.addAttribute(VERSION_KEY, version);
        }
        if (CommonUtils.isNotEmpty(datas)) {
            datas.forEach(data -> root.add(data.transfer()));
        }
        return root;
    }

}
