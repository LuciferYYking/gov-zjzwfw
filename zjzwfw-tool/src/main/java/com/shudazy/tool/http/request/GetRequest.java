package com.shudazy.tool.http.request;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * GET
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class GetRequest extends BaseRequest{

    public GetRequest(String uri) {
        super(uri);
    }

    @Override
    public String getRequestUrl() {
        String queryParams = this.parameters
                .entrySet()
                .stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        return queryParams.isEmpty() ? requestUri : requestUri + "?" + queryParams;
    }

    @Override
    public HttpUriRequest createHttpRequest() {
        HttpGet httpGet = new HttpGet(this.getRequestUrl());
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(super.getTimeout())
                .setConnectTimeout(super.getTimeout())
                .setConnectionRequestTimeout(super.getTimeout())
                .build();
        httpGet.setConfig(requestConfig);
        this.headers.forEach(httpGet::setHeader);
        return httpGet;
    }

}
