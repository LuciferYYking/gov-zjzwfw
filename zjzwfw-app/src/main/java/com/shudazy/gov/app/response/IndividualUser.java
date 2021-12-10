package com.shudazy.gov.app.response;

import com.shudazy.gov.app.client.AppResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 个人用户
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Getter
@Setter
@ToString
public class IndividualUser extends AppResponse {

    /**
     * 用户在SSO分配的身份唯一号
     */
    private String userid;

    /**
     * 登录账号
     */
    private String loginname;

    /**
     * 真实姓名
     */
    private String username;

    /**
     * 认证级别：
     *  1 非实名
     *  2 初级实名
     *  3 高级实名
     */
    private String authlevel;

    /**
     * 注册证件类型：
     *  1 身份证
     *  2 普通护照
     *  3 军官证
     *  4 港澳居民往来内地通行证
     *  5 台湾居民往来内地通行证
     *  6 大陆公民来往港澳通行证
     *  7 大陆公民来往台湾通行证
     *  8 外国人永久居留证
     */
    private String idtype;

    /**
     * 注册证件号码
     */
    private String idnum;

    /**
     * 护照
     */
    private String passport;

    /**
     * 港澳居民来往大陆通行证
     */
    private String permitlicense;

    /**
     * 台湾居民来往内地通行证
     */
    private String taiwanlicense;

    /**
     * 军官证
     */
    private String officerlicense;

    /**
     * 外国人永久居留身份证
     */
    private String greencard;

    /**
     * 性别：
     *  1 男
     *  2 女
     */
    private String sex;

    /**
     * 民族
     */
    private String nation;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * CA证书 KEY
     */
    private String cakey;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 国籍
     */
    private String country;

    /**
     * 省籍
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 办公地址
     */
    private String officeaddress;

    /**
     * 办公电话
     */
    private String officephone;

    /**
     * 办公传真
     */
    private String officefax;

    /**
     * 家庭电话
     */
    private String homephone;

    /**
     * 家庭地址
     */
    private String homeaddress;

    /**
     * 用户激活状态：
     *  1 激活
     *  2 未激活
     */
    private String useable;

    /**
     * 排序
     */
    private String orderby;

    /**
     * 头像地址
     */
    private String headpicture;

}
