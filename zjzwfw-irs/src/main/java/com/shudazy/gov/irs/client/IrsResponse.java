package com.shudazy.gov.irs.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 * @author Bing D. Yee
 * @since 2021/09/30
 */
@Getter
@Setter
@ToString
public class IrsResponse<T> implements Serializable {

    private static final long serialVersionUID = -3175023877304845814L;

    private String code;

    private String msg;

    private int dataCount;

    private String requestId;

    private Object datas;

    public boolean isSuccess() {
        return "00".equals(code);
    }

}
