package org.warless.xender.constant;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/9
 */
public enum XmlEntryEnum {
    /***/
    TAB("I010032", "\t", "列分隔符(缺少值时默认为制表符)"),
    ENTER("I010033", "\n", "行分隔符(缺少值时默认为换行符)"),
    DATASET_CODE("A010004", "", "数据集代码"),
    DATA_SOURCE("B050016", "", "数据来源"),
    ORGANIZATION_CODE("G020013", "", "厂商或者场所组织机构代码"),
    COLLECTION_PLACE("F010008", "", "数据采集地"),
    BEGIN_LINE("I010038", "１", "数据起始行，可选项，不填写默认为第１行"),
    CHARSET("I010039", "UTF-8", "可选项，默认为UTF-８，BCP文件编码格式（采用不带格式的编码方式，如：UTF-８无BOM）"),
    BCP_FILE_PATH("H040003", "", "文件路径"),
    BCP_FILE_NAME("H010020", "", "文件名"),
    LINE_COUNT("I010034", "", "记录行数");

    private String key;
    private String defaultValue;
    private String remark;

    private XmlEntryEnum(String key, String value, String remark) {
        this.key = key;
        this.defaultValue = value;
        this.remark = remark;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getRemark() {
        return remark;
    }

}
