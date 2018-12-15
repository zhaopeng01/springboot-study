/**
 *
 */
package com.zyc.exception;


import com.zyc.common.HttpCode;

/**
 * 400请求参数出错
 */
@SuppressWarnings("serial")
public class IllegalParameterException extends BaseException {
    public IllegalParameterException() {
    }

    public IllegalParameterException(Throwable ex) {
        super(ex);
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable ex) {
        super(message, ex);
    }

    protected HttpCode getCode() {
        return HttpCode.BAD_REQUEST;
    }
}
