package org.warless.xender.framework.xml;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.warless.xender.framework.XmlElement;


/**
 * Item
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/9
 */
public class Item implements XmlElement {

    public static final String TAG_NAME = "ITEM";
    public static final String KEY_KEY = "key";
    public static final String VALUE_KEY = "val";
    public static final String REMARK_KEY = "rmk";

    private String key;
    private String value;
    private String remark;

    public String getKey() {
        return key;
    }

    public Item setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Item setValue(String value) {
        this.value = value;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Item setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public Element transfer() {
        Element root = DocumentHelper.createElement(TAG_NAME);
        root.addAttribute(KEY_KEY, key);
        root.addAttribute(VALUE_KEY, value);
        root.addAttribute(REMARK_KEY, remark);
        return root;
    }

}
