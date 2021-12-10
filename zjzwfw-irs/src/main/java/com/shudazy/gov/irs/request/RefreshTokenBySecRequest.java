package com.shudazy.gov.irs.request;

import com.shudazy.gov.irs.client.IrsRequest;
import com.shudazy.gov.irs.response.GetRefreshTokenResponse;
import com.shudazy.tool.http.RequestMethod;

import java.util.Map;

/**
 * @author Bing D. Yee
 * @since 2021/10/07
 */
public class RefreshTokenBySecRequest implements IrsRequest<GetRefreshTokenResponse> {

    @Override
    public String getApiMethod() {
        return "/gateway/app/refreshTokenBySec.htm";
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public Map<String, String> getRequestParams() {
        return Map.of();
    }

    @Override
    public Class<GetRefreshTokenResponse> getResponseClass() {
        return GetRefreshTokenResponse.class;
    }

}
