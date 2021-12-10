package com.shudazy.tool.http.request;

import com.shudazy.tool.base.StringUtils;
import com.shudazy.tool.http.sign.SignStrategy;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础请求
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public abstract class BaseRequest {

    protected final int timeout;
    protected Map<String, String> headers;
    protected Map<String, String> parameters;
    protected final String requestUri;
    private Class<? extends SignStrategy> signStrategyClass;

    public BaseRequest(String uri) {
        this.requestUri = uri;
        this.timeout = 5000;
        this.headers = new HashMap<>();
        this.parameters = new HashMap<>();
    }

    public<T extends SignStrategy> void setSignStrategyClass(Class<T> signStrategyClass) {
        this.signStrategyClass = signStrategyClass;
    }

    public void setHeaders(Map<String, String> headers) {
        if (!headers.isEmpty()) {
            this.headers = headers;
        }
    }

    public void addParameters(Map<String, String> parameters) {
        if (!parameters.isEmpty()) {
            this.parameters.putAll(parameters);
        }
    }

    public BaseRequest addHeader(String name, String value) {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
            this.headers.put(name, value);
        }
        return this;
    }

    public BaseRequest addParameter(String name, String value) {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
            this.parameters.put(name, value);
        }
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getRequestUrl() {
        return this.requestUri;
    }

    /**
     * 创建请求
     *
     * @return  HTTP请求
     */
    public abstract HttpUriRequest createHttpRequest();

    public boolean support(SignStrategy strategy) {
        return this.signStrategyClass != null && strategy.getClass().isAssignableFrom(this.signStrategyClass);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

}
