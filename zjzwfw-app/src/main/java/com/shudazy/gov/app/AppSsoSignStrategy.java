package com.shudazy.gov.app;

import com.alibaba.fastjson.JSONObject;
import com.shudazy.tool.base.DigestHelper;
import com.shudazy.tool.http.HttpConstant;
import com.shudazy.tool.http.request.BaseRequest;
import com.shudazy.tool.http.sign.SignStrategy;

/**
 * 法人SSO 签名
 *
 * @author Bing D. Yee
 * @since 2021/12/10
 */
public class AppSsoSignStrategy extends SignStrategy {

    public AppSsoSignStrategy(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    @Override
    public void signRequest(BaseRequest request) {
        String param = JSONObject.toJSONString(request.getParameters());
        String signature = DigestHelper.hmacSha256(param, this.secretKey);
        request.addHeader("x-esso-project-id", this.accessKey)
                .addHeader("x-esso-signature", signature)
                .addHeader("Content-Type", HttpConstant.APPLICATION_JSON);
    }

}
