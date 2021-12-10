package com.shudazy.gov.ding.client;

import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.shudazy.gov.ding.request.GetDingTokenRequest;
import com.shudazy.gov.ding.response.DingToken;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class DingClient {

    private static final ExecutableClient EXECUTABLE_CLIENT = ExecutableClient.getInstance();

    private final String accessKey;
    private final String secretKey;

    public DingClient(String accessKey, String secretKey, String domain, String protocol) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        DingClient.EXECUTABLE_CLIENT.setAccessKey(accessKey);
        DingClient.EXECUTABLE_CLIENT.setSecretKey(secretKey);
        DingClient.EXECUTABLE_CLIENT.setDomainName(domain);
        DingClient.EXECUTABLE_CLIENT.setProtocal(protocol);
        DingClient.EXECUTABLE_CLIENT.init();
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public ExecutableClient getExecutableClient() {
        return EXECUTABLE_CLIENT;
    }

    public <T> T execute(DingRequest<T> request) {
        return request.execute(this);
    }

    public void destroy() {
        DingClient.EXECUTABLE_CLIENT.destroy();
    }

    public static void main(String[] args) {
        DingClient dingClient = new DingClient("yichujiban-5h14OQO9uH7MlJneqQz", "OXA12q4F1l2m2OAf4m3i05R51w942dsY88ds11CE", "openplatform.dg-work.cn", "https");
        DingToken token = dingClient.execute(new GetDingTokenRequest());
        System.err.println(token);
        dingClient.destroy();
    }

}
