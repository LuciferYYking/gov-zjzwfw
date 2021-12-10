package com.shudazy.gov.irs.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shudazy.gov.irs.IrsSignStrategy;
import com.shudazy.gov.irs.request.GetMdJyMxcxRequest;
import com.shudazy.gov.irs.request.RefreshTokenByKeyRequest;
import com.shudazy.gov.irs.response.GetMdJyMxcxResponse;
import com.shudazy.gov.irs.response.GetRefreshTokenResponse;
import com.shudazy.gov.irs.response.YbxtResponse;
import com.shudazy.tool.exception.ServiceInvokeException;
import com.shudazy.tool.http.SignableHttpClient;
import com.shudazy.tool.http.request.BaseRequest;
import com.shudazy.tool.http.request.GetRequest;
import com.shudazy.tool.http.request.PostRequest;

import java.util.List;

/**
 * IRS Client
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class IrsClient {

    private final SignableHttpClient httpClient;
    private final String url;

    public IrsClient(String url, String accessKey, String secretKey, SignableHttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;
        this.httpClient.registerSignStrategy(new IrsSignStrategy(accessKey, secretKey));
    }

    public <T> T execute(IrsRequest<T> request) {
        BaseRequest httpRequest;
        String requestUrl = this.url + request.getApiMethod();
        switch (request.getRequestMethod()) {
            case GET:
                httpRequest = new GetRequest(requestUrl);
                break;
            case POST:
                httpRequest = new PostRequest(requestUrl);
                break;
            default:
                throw new UnsupportedOperationException("不支持的请求类型 - " + request.getRequestMethod());
        }
        httpRequest.setSignStrategyClass(IrsSignStrategy.class);
        httpRequest.addParameters(request.getRequestParams());
        String respStr = this.httpClient.execute(httpRequest);
        IrsResponse<T> response = JSON.parseObject(respStr, new TypeReference<>() {});
        if (!response.isSuccess()) {
            throw new ServiceInvokeException("【调用IRS平台接口失败】" + response.getMsg());
        }
        String data = response.getDatas() instanceof String ? response.getDatas().toString() : JSONObject.toJSONString(response.getDatas());
        return JSONObject.parseObject(data, request.getResponseClass());
    }

    public static void main(String[] args) {
        SignableHttpClient httpClient = SignableHttpClient.getInstance();
        httpClient.init();
        IrsClient client = new IrsClient("http://192.168.17.121:30852/mock/11", "abc", "abc", httpClient);
        GetMdJyMxcxRequest request = new GetMdJyMxcxRequest();
        request.setYltcdjh("dddd");
        YbxtResponse<GetMdJyMxcxResponse> response = client.execute(request);
        System.err.println(response.getBody());
        httpClient.destroy();
    }

}
