package com.shudazy.gov.app.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Getter
@Setter
@ToString
public class AppResponse {

    public static String OK = "0";

    /**
     * 结果：
     *  0 成功
     *  6501 令牌已经超时失效
     *  6502 该令牌不存在
     *  6503 获取用户信息失败
     *  6504 令牌不属于改接入资源
     *  6505 令牌格式不对
     *  6599 其它错误
     */
    private String result;

    /**
     * 错误说明
     */
    private String errmsg;

    public boolean isSucceed() {
        return OK.equals(result);
    }

}
