package com.shudazy.gov.app.response;

import com.shudazy.gov.app.client.AppResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * 获取令牌请求返回数据
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Getter
@Setter
public class Token extends AppResponse {

    /**
     * 令牌
     */
    private String token;

}
