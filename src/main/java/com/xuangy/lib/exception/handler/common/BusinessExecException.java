package com.xuangy.lib.exception.handler.common;

/**
 * @description: 业务逻辑异常
 * @author: xuanguangyao
 * @email: wtopps111@gmail.com
 * @date: 2021/12/20 14:36
 */
public class BusinessExecException extends RuntimeException {

    public BusinessExecException() {
        super();
    }

    public BusinessExecException(String actionName) {
        super("发生异常: " + actionName);
    }

    public BusinessExecException(String actionName, Throwable cause) {
        super("发生异常: " + (actionName == null ? "" : actionName), cause);
    }

    public BusinessExecException(Throwable cause) {
        super(cause);
    }
}
