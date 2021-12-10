package com.shudazy.tool.exception;

/**
 * 请求调用失败异常
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class ServiceInvokeException extends RuntimeException {

    private static final long serialVersionUID = -2654082656942183861L;

    public ServiceInvokeException() {
        super("HTTP请求调用失败");
    }

    public ServiceInvokeException(String message) {
        super(message);
    }

    public ServiceInvokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceInvokeException(Throwable cause) {
        super(cause);
    }

    public ServiceInvokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}