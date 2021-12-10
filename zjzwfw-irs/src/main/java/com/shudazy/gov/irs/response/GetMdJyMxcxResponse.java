package com.shudazy.gov.irs.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bing D. Yee
 * @since 2021/10/10
 */
@Getter
@Setter
@ToString
public class GetMdJyMxcxResponse {

    private static final long serialVersionUID = 7672896382328497938L;

    /**
     * 是否报销完成
     */
    private Boolean sfbxwc;

    /**
     * 费用结算日期
     */
    private String fyjsrq;

    /**
     * 总费用
     */
    private String zfy;

    /**
     * 医保应支付金额
     */
    private String ybyzfje;

    /**
     * 医保实际支付金额
     */
    private String ybsjzfje;


}
