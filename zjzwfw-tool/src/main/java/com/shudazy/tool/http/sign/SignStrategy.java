package com.shudazy.tool.http.sign;

import com.shudazy.tool.http.request.BaseRequest;

/**
 * 签名策略
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public abstract class SignStrategy {

    protected final String accessKey;
    protected final String secretKey;

    public SignStrategy(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 对请求进行签名
     *
     * @param request 请求
     */
    public abstract void signRequest(BaseRequest request);

}
