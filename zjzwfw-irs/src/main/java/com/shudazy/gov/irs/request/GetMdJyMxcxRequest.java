package com.shudazy.gov.irs.request;


import com.shudazy.gov.irs.client.IrsRequest;
import com.shudazy.gov.irs.response.GetMdJyMxcxResponse;
import com.shudazy.gov.irs.response.YbxtResponse;
import com.shudazy.tool.http.RequestMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取医保报销结果
 *
 * @author Bing D. Yee
 * @since 2021/10/10
 */
@Getter
@Setter
@ToString
public class GetMdJyMxcxRequest implements IrsRequest<YbxtResponse<GetMdJyMxcxResponse>> {

    /**
     * 医疗统筹单据号
     */
    private String yltcdjh;

    @Override
    public String getApiMethod() {
        return "/gateway/api/001008013008158/windowSystem/e29Fe87o9dA1MDv6.htm";
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put("yltcdjh", this.yltcdjh);
        return params;
    }

    @Override
    public Class<YbxtResponse<GetMdJyMxcxResponse>> getResponseClass() {
        return (Class<YbxtResponse<GetMdJyMxcxResponse>>) new YbxtResponse<>().getClass();
    }

}
