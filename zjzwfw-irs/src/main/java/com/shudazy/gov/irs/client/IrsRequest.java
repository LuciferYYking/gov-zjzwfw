package com.shudazy.gov.irs.client;



import com.alibaba.fastjson.TypeReference;
import com.shudazy.tool.http.RequestMethod;

import java.util.Map;

/**
 * 浙里办请求
 *
 * @author Bing D. Yee
 * @since 2021/09/30
 */
public interface IrsRequest<T> {

    /**
     * 获取接口路径
     *
     * @return 接口路径
     */
    String getApiMethod();

    /**
     * 获取请求方式
     *
     * @return 请求方式
     */
    RequestMethod getRequestMethod();

    /**
     * 获取请求参数
     *
     * @return 请求参数
     */
    Map<String, String> getRequestParams();

    /**
     * 获取返回数据类型
     *
     * @return 返回数据类型
     */
    Class<T> getResponseClass();

}
