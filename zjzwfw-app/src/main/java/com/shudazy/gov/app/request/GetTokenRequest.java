package com.shudazy.gov.app.request;

import com.shudazy.gov.app.client.AppRequest;
import com.shudazy.gov.app.response.Token;
import com.shudazy.tool.http.RequestMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 获取令牌请求
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Getter
@Setter
@ToString
public class GetTokenRequest implements AppRequest<Token> {

    private String st;

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>(3);
        params.put("method", "ticketValidation");
        params.put("st", this.st);
        return params;
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public Class<Token> getResponseClass() {
        return Token.class;
    }

}
