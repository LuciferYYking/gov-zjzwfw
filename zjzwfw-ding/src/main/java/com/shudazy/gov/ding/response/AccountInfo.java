package com.shudazy.gov.ding.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Bing D. Yee
 * @since 2021/09/09
 */
@Getter
@Setter
@ToString
public class AccountInfo {

    /**
     * 账号名
     */
    private String account;

    /**
     * 账号id
     */
    private String accountId;

    /**
     * 应用名
     */
    private String clientId;

    /**
     * 租户下人员编码
     */
    private String employeeCode;

    /**
     * 姓名
     */
    private String lastName;

    /**
     * 账号类型
     */
    private String namespace;

    /**
     * 昵称
     */
    private String nickNameCn;

    /**
     * 租户ID
     */
    private Long realmId;

    /**
     * 租户名称
     */
    private String realmName;

    /**
     * 租户+用户唯一标识
     */
    private String tenantUserId;

    /**
     * 应用+用户唯一标识
     */
    private String openid;

}
