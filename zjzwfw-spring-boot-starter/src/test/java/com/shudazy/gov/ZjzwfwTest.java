package com.shudazy.gov;

import com.shudazy.gov.app.client.AppClient;
import com.shudazy.gov.app.request.GetTokenRequest;
import com.shudazy.gov.app.response.Token;
import com.shudazy.gov.ding.client.DingClient;
import com.shudazy.gov.ding.request.GetDingTokenRequest;
import com.shudazy.gov.ding.response.DingToken;
import com.shudazy.gov.irs.client.IrsClient;
import com.shudazy.gov.irs.request.GetMdJyMxcxRequest;
import com.shudazy.gov.irs.response.GetMdJyMxcxResponse;
import com.shudazy.gov.irs.response.YbxtResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@SpringBootTest(classes = ZjzwfwAutoConfiguration.class)
public class ZjzwfwTest {

//    @Resource
    private AppClient appClient;

    @Resource
    private IrsClient irsClient;

//    @Resource
    private DingClient dingClient;

    @Test
    public void testAppClient() {
        GetTokenRequest request = new GetTokenRequest();
        request.setSt("ssss");
        Token response = appClient.execute(request);
        System.err.println(response);
    }

    @Test
    public void testIrsClient() {
        GetMdJyMxcxRequest request = new GetMdJyMxcxRequest();
        request.setYltcdjh("dddd");
        YbxtResponse<GetMdJyMxcxResponse> response = irsClient.execute(request);
        System.err.println(response.getBody());
    }

    @Test
    public void testDingClient() {
        DingToken token = dingClient.execute(new GetDingTokenRequest());
        System.err.println(token);
    }

}
