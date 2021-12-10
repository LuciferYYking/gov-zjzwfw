package com.shudazy.gov.app.request;

import com.shudazy.gov.app.client.AppRequest;
import com.shudazy.gov.app.response.IndividualUser;
import com.shudazy.tool.http.RequestMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取用户信息请求
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Getter
@Setter
@ToString
public class GetUserInfoRequest implements AppRequest<IndividualUser> {

    private String token;

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>(3);
        params.put("method", "getUserInfo");
        params.put("token", this.token);
        return params;
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public Class<IndividualUser> getResponseClass() {
        return IndividualUser.class;
    }

}
