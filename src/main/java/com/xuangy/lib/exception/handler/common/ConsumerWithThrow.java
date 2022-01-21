package com.xuangy.lib.exception.handler.common;

import java.util.Objects;

/**
 * @description: 带有 Throw 的 Consumer 函数式接口
 * @author: xuanguangyao
 * @email: wtopps111@gmail.com
 * @date: 2021/12/20 14:36
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface ConsumerWithThrow<T, E extends Exception> extends SerialFunctional{

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     * @throws E accept 中需要抛出的异常
     */
    void accept(T t) throws E;

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default ConsumerWithThrow<T, E> andThen(java.util.function.Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default ConsumerWithThrow<T, E> andThen(ConsumerWithThrow<? super T, ? extends E> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
