package com.gary.myspring.exception;

/**
 * describe:Bean重复定义异常
 *
 * @author
 * @date 2019/01/16
 */
public class BeanAlreadyDefineException extends Exception {
    public BeanAlreadyDefineException() {
        super();
    }

    public BeanAlreadyDefineException(String message) {
        super(message);
    }

    public BeanAlreadyDefineException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanAlreadyDefineException(Throwable cause) {
        super(cause);
    }

    protected BeanAlreadyDefineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
