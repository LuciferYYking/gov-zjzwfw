package com.shudazy.gov.irs.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 医保系统返回结果
 *
 * @author Bing D. Yee
 * @since 2021/09/30
 */
@Getter
@Setter
@ToString
public class YbxtResponse<T> implements Serializable {

    private static final long serialVersionUID = -3175023877304855814L;

    private static final String SUCCESS = "1";

    private String msg;

    private String flag;

    private List<T> body;

    public boolean isSuccess() {
        return SUCCESS.equals(flag);
    }

}
