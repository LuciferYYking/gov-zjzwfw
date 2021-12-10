package com.shudazy.gov.app.client;

import com.alibaba.fastjson.JSONObject;
import com.shudazy.gov.app.AppSignStrategy;
import com.shudazy.gov.app.AppSsoSignStrategy;
import com.shudazy.gov.app.request.GetTokenRequest;
import com.shudazy.gov.app.response.Token;
import com.shudazy.tool.base.StringUtils;
import com.shudazy.tool.http.SignableHttpClient;
import com.shudazy.tool.http.request.BaseRequest;
import com.shudazy.tool.http.request.GetRequest;
import com.shudazy.tool.http.request.PostRequest;
import com.shudazy.tool.http.sign.SignStrategy;

/**
 * 浙里办请求客户端
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class AppClient {

    private final SignableHttpClient httpClient;
    private final String url;

    public AppClient(String url, String accessKey, String secretKey, SignableHttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;
        this.httpClient.registerSignStrategy(new AppSignStrategy(accessKey, secretKey));
        this.httpClient.registerSignStrategy(new AppSsoSignStrategy(accessKey, secretKey));
    }

    public <T> T execute(AppRequest<T> request, Class<? extends SignStrategy> signStrategyClass) {
        BaseRequest httpRequest;
        switch (request.getRequestMethod()) {
            case GET:
                httpRequest = new GetRequest(url);
                break;
            case POST:
                httpRequest = new PostRequest(url);
                break;
            default:
                throw new UnsupportedOperationException("不支持的请求类型 - " + request.getRequestMethod());
        }
        httpRequest.setSignStrategyClass(signStrategyClass);
        httpRequest.addParameters(request.getRequestParams());
        String response = this.httpClient.execute(httpRequest);
        return JSONObject.parseObject(response, request.getResponseClass());
    }

    public <T> T execute(AppRequest<T> request) {
        return this.execute(request, AppSignStrategy.class);
    }

    public static void main(String[] args) {
        SignableHttpClient httpClient = SignableHttpClient.getInstance();
        httpClient.init();
        AppClient client = new AppClient("https://appapi.zjzwfw.gov.cn/sso/servlet/simpleauth", "ycjb", "ycjbpwd", httpClient);
        GetTokenRequest request = new GetTokenRequest();
        request.setSt("ssss");
        Token response = client.execute(request);
        System.err.println(response);
        httpClient.destroy();
    }

}
