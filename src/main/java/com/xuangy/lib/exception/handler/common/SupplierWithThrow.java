package com.xuangy.lib.exception.handler.common;

/**
 * @description: 带有抛出异常的 Supplier 接口
 * @author: xuanguangyao
 * @email: wtopps111@gmail.com
 * @date: 2021/12/20 14:36
 * @see java.util.function.Consumer
 */

@FunctionalInterface
public interface SupplierWithThrow<T, E extends Exception> extends SerialFunctional{

    T get() throws E;
}
