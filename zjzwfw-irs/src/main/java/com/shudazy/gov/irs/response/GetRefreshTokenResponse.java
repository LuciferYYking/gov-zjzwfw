package com.shudazy.gov.irs.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Bing D. Yee
 * @since 2021/09/30
 */
@Getter
@Setter
@ToString
public class GetRefreshTokenResponse {

    private static final long serialVersionUID = -7689789046939393542L;

    /**
     * 刷新密钥
     */
    private String refreshSecret;

    private Long refreshSecretEndTime;

    /**
     * 请求密钥
     */
    private String requestSecret;

    private Long requestSecretEndTime;

}
