package com.shudazy.gov.app.client;

import com.shudazy.tool.http.RequestMethod;

import java.util.Map;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public interface AppRequest<T> {

    /**
     * 获取请求参数
     *
     * @return 请求参数
     */
    Map<String, String> getRequestParams();

    /**
     * 请求方式
     *
     * @return 请求方式
     */
    RequestMethod getRequestMethod();

    /**
     * 获取返回数据类型
     *
     * @return 返回数据类型
     */
    Class<T> getResponseClass();

    default String getContentType() {
        return null;
    }

}
