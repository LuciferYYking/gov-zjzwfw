package com.shudazy.gov.ding.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.xxpt.gateway.shared.api.request.OapiRpcOauth2DingtalkAppUserJsonRequest;
import com.alibaba.xxpt.gateway.shared.api.response.OapiRpcOauth2DingtalkAppUserJsonResponse;
import com.alibaba.xxpt.gateway.shared.client.http.IntelligentPostClient;
import com.shudazy.gov.ding.client.DingClient;
import com.shudazy.gov.ding.client.DingRequest;
import com.shudazy.gov.ding.response.AccountInfo;
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
public class GetAccountInfoRequest implements DingRequest<AccountInfo> {

    private String accessToken;

    private String authCode;

    @Override
    public AccountInfo execute(DingClient dingClient) {
        IntelligentPostClient intelligentPostClient = dingClient.getExecutableClient().newIntelligentPostClient("/rpc/oauth2/dingtalk_app_user.json");
        OapiRpcOauth2DingtalkAppUserJsonRequest oapiRpcOauth2DingtalkAppUserJsonRequest = new OapiRpcOauth2DingtalkAppUserJsonRequest();
        oapiRpcOauth2DingtalkAppUserJsonRequest.setAccess_token(this.accessToken);
        oapiRpcOauth2DingtalkAppUserJsonRequest.setAuth_code(this.authCode);
        OapiRpcOauth2DingtalkAppUserJsonResponse apiResult = intelligentPostClient.post(oapiRpcOauth2DingtalkAppUserJsonRequest);
        if (!apiResult.getSuccess()) {
            throw new RuntimeException("获取用户信息失败");
        }
        JSONObject jsonObject = JSON.parseObject(apiResult.getContent());
        return jsonObject.getJSONObject("content").getObject("data", AccountInfo.class);
    }

}
