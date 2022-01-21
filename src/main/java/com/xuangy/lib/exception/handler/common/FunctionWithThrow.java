package com.xuangy.lib.exception.handler.common;

import java.util.Objects;
import java.util.function.Function;

/**
 * @description: 带有 Throw 的 Function 函数式接口
 * @author: xuanguangyao
 * @email: wtopps111@gmail.com
 * @date: 2021/12/20 14:36
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface FunctionWithThrow<T, R, E extends Exception> extends SerialFunctional{

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws E apply 函数中需要抛出的异常
     */
    R apply(T t) throws E;

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>    the type of input to the {@code before} function, and to the
     *               composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     */
    default <V> FunctionWithThrow<V, R, E> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     * @see #compose(Function)
     */
    default <V> FunctionWithThrow<T, V, E> andThen(FunctionWithThrow<? super R, ? extends V, E> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> the type of the input and output of the operator
     * @return a unary operator that always returns its input argument
     */
    static <T> FunctionWithThrow<T, T, Exception> identity() {
        return t -> t;
    }
}
