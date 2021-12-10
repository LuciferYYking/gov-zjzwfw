package com.shudazy.tool.http.sign;

import com.shudazy.tool.base.DigestHelper;
import com.shudazy.tool.http.request.BaseRequest;

import java.util.Random;

/**
 * 默认签名策略
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class DefaultSignStrategy extends SignStrategy {

    public DefaultSignStrategy(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    @Override
    public void signRequest(BaseRequest request) {
        String timestamp = System.currentTimeMillis() + "";
        String nonce = new Random().nextInt() + "";
        request.addParameter("nonce", nonce)
                .addParameter("timestamp", timestamp)
                .addParameter("appKey", this.accessKey)
                .addParameter("sign", DigestHelper.md5hex(this.secretKey + timestamp + nonce));
    }

}
