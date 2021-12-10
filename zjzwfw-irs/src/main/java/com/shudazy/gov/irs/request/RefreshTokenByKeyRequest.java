package com.shudazy.gov.irs.request;

import com.shudazy.gov.irs.client.IrsRequest;
import com.shudazy.gov.irs.response.GetRefreshTokenResponse;
import com.shudazy.tool.http.RequestMethod;

import java.util.Map;

/**
 * description:
 *
 * @author Bing D. Yee
 * @since 2021/09/30
 */
public class RefreshTokenByKeyRequest implements IrsRequest<GetRefreshTokenResponse> {

    @Override
    public String getApiMethod() {
        return "/gateway/app/refreshTokenByKey.htm";
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
