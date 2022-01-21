package com.xuangy.lib.exception.handler.common;

/**
 * @description: 带有 Throw 的 Runnable 函数式接口
 * @author: xuanguangyao
 * @email: wtopps111@gmail.com
 * @date: 2021/12/20 14:36
 * @see java.util.function.Consumer
 */

@FunctionalInterface
public interface RunnableWithThrow<E extends Exception> extends SerialFunctional{

    void run() throws E;

}
