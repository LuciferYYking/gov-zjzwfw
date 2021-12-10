package com.shudazy.tool.http.request;

import com.alibaba.fastjson.JSONObject;
import com.shudazy.tool.http.HttpConstant;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.stream.Collectors;

/**
 * POST
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class PostRequest extends BaseRequest{

    private String contentType;

    public PostRequest(String uri) {
        this(uri, null);
    }

    public PostRequest(String uri, String contentType) {
        super(uri);
        this.contentType = contentType;
    }

    @Override
    public HttpUriRequest createHttpRequest() {
        StringEntity entity;
        if (HttpConstant.APPLICATION_JSON.equals(this.contentType)) {
            entity = new StringEntity(JSONObject.toJSONString(this.parameters), Consts.UTF_8);
        } else {
            List<NameValuePair> nameValues = this.parameters.entrySet()
                    .stream()
                    .map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
            entity = new UrlEncodedFormEntity(nameValues, Consts.UTF_8);
        }
        HttpPost httpPost = new HttpPost(this.getRequestUrl());
        httpPost.setEntity(entity);
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(super.getTimeout())
                .setConnectTimeout(super.getTimeout())
                .setConnectionRequestTimeout(super.getTimeout())
                .build();
        httpPost.setConfig(requestConfig);
        this.headers.forEach(httpPost::addHeader);
        return httpPost;
    }

}
