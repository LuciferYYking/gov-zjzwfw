package com.shudazy.gov.ding.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.xxpt.gateway.shared.api.request.OapiGettokenJsonRequest;
import com.alibaba.xxpt.gateway.shared.api.response.OapiGettokenJsonResponse;
import com.alibaba.xxpt.gateway.shared.client.http.IntelligentGetClient;
import com.shudazy.gov.ding.client.DingClient;
import com.shudazy.gov.ding.client.DingRequest;
import com.shudazy.gov.ding.response.DingToken;

/**
 * description:
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class GetDingTokenRequest implements DingRequest<DingToken> {

    @Override
    public DingToken execute(DingClient dingClient) {
        IntelligentGetClient intelligentGetClient = dingClient.getExecutableClient().newIntelligentGetClient("/gettoken.json");
        OapiGettokenJsonRequest oapiGettokenJsonRequest = new OapiGettokenJsonRequest();
        oapiGettokenJsonRequest.setAppkey(dingClient.getAccessKey());
        oapiGettokenJsonRequest.setAppsecret(dingClient.getSecretKey());
        OapiGettokenJsonResponse apiResult = intelligentGetClient.get(oapiGettokenJsonRequest);
        if (!apiResult.getSuccess() || !apiResult.getContent().getSuccess()) {
            throw new RuntimeException("获取应用access_token失败");
        }
        String data = apiResult.getContent().getData();
        return JSON.parseObject(data, DingToken.class);
    }

}
