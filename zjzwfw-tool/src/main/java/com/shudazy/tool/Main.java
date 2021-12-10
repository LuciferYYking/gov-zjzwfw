package com.shudazy.tool;

import com.shudazy.tool.http.SignableHttpClient;
import com.shudazy.tool.http.request.BaseRequest;
import com.shudazy.tool.http.request.GetRequest;
import com.shudazy.tool.http.sign.DefaultSignStrategy;

/**
 * description:
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class Main {

    public static void main(String[] args) {
        SignableHttpClient httpClient = SignableHttpClient.getInstance();
        httpClient.init();
        httpClient.registerSignStrategy(new DefaultSignStrategy("abc", "abc"));
        BaseRequest request = new GetRequest("https://www.baidu.com");
        String resp = httpClient.execute(request);
        System.err.println(resp);
        httpClient.destroy();
    }

}
