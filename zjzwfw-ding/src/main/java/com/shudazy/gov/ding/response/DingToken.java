package com.shudazy.gov.ding.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 访问令牌
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Getter
@Setter
@ToString
public class DingToken {

    private Integer expiresIn;

    private String accessToken;

}
