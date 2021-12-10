package com.shudazy.gov.app.response;

import com.shudazy.gov.app.client.AppResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 法人用户
 *
 * @author Bing D. Yee
 * @since 2021/12/10
 */
@Getter
@Setter
@ToString
public class CorporateUser extends AppResponse {

    /**
     * 工商注册号
     */
    private String CompanyRegNumber;

    /**
     * 机构代码号
     */
    private String OrganizationNumber;

    /**
     * 法人名称（企业或机构名称）
     */
    private String CompanyName;

    /**
     * 实体类型:1:企业、农专社、个体工商户；2:其他非法人单位；4：事业单位；5：行政单位；" +
     *             "6：社会团体；7：民办非企业法人; 8：基金会；9：养老机构；11：律师事务所
     */
    private String OrgType;

    /**
     * 法人类型
     */
    private String CompanyType;

    /**
     * 注册地址
     */
    private String CompanyAddress;

    /**
     * 法人电话号码
     */
    private String telephoneNumber;

    /**
     * 经营范围
     */
    private String CompanyScope;

    /**
     * 登记机构（中文）
     */
    private String CompanyRegOrg;

    /**
     * 工商内部序号
     */
    private String CompanySerialNumber;

    /**
     * 用户ID，唯一标识
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 证书名称
     */
    private String certName;

    /**
     * 证书内部序号
     */
    private String certNbxh;

    /**
     * 证书注册号
     */
    private String certRegNo;

    /**
     * 证书编号
     */
    private String certHxbh;

    /**
     * 十六进制数字证书序列号
     */
    private String certSn;

    /**
     * 十进制数字证书序列号
     */
    private String certDecSn;

    /**
     * 证书内容BASE64
     */
    private String certBase64;

    /**
     * 登录类型:Cert：数字证书方式登录 Password：用户名密码方式登录 Elicense：电子营业执照方式登录
     */
    private String loginType;

    /**
     * 实名认证等级 1：第一级数字证书和电子营业执照 2：第二级现场用户名密码实名认证 " +
     *             "3：第三级在线用户名密码实名认证 4：第四级用户名密码非实名认证
     */
    private String realLevel;

    /**
     * 行政区划编码
     */
    private String xzqh;

    /**
     * 统一社会信用代码
     */
    private String uniscid;

    /**
     * 法定代表人/负责人
     */
    private String CompanyLegRep;

    /**
     * 法人状态
     */
    private String status;

    /**
     * 经办人手机号
     */
    private String attnPhone;

    /**
     * 经办人姓名
     */
    private String attnName;

    /**
     * 经办人证件类型 51：身份证（老身份证类型）（数字证书用户登录时可能为空） 111：身份证（新身份证类型）" +
     *             "414：普通护照； 516：港澳居民来往内地通行证； 511：台湾居民来往内地通行证； 553：外国人永久居留身份证
     */
    private String attnIDType;

    /**
     * 经办人身份证件号
     */
    private String attnIDNo;

    /**
     * 经办人固定电话
     */
    private String attnLandLinePhone;

}
