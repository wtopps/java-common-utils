package com.xuangy.lib.exception.handler;

import com.xuangy.lib.exception.handler.common.BusinessExecException;
import com.xuangy.lib.exception.handler.common.ConsumerWithThrow;
import com.xuangy.lib.exception.handler.common.RunnableWithThrow;
import com.xuangy.lib.exception.handler.common.SupplierWithThrow;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 简化代码类, 对冗余的代码进行简化, 增强可读性
 *               ignoreException开头, 忽略发生的异常, 仅仅打印日志.
 *               runtimeException开头, 原来需要catch的运行时转换为runtimeException抛出.
 *               支持自定义业务异常
 * @author: xuanguangyao
 * @email: wtopps111@gmail.com
 * @date: 2021/12/20 14:36
 */
@Slf4j
public class ExceptionHandler {

    private ExceptionHandler() {
    }


    /**
     * 忽略运行的异常
     *
     * @param runnable 带有异常的运行接口
     * @param message  发生异常报错信息
     * @see ExceptionHandler#simpleExceptionForRun(RunnableWithThrow, String, boolean)
     */
    public static void ignoreExceptionWithErrorMsg(RunnableWithThrow<? extends Exception> runnable, String message) {
        simpleExceptionForRun(runnable, message, false);
    }

    /**
     * 忽略运行的异常
     *
     * @param runnable 带有异常的运行接口
     * @see ExceptionHandler#simpleExceptionForRun(RunnableWithThrow, String, boolean)
     */
    public static void ignoreException(RunnableWithThrow<? extends Exception> runnable) {
        simpleExceptionForRun(runnable, null, false);
    }

    /**
     * 异常转换为 ActionExecException
     *
     * @param runnable 带有异常的运行接口
     * @param message  发生异常报错信息
     * @see ExceptionHandler#simpleExceptionForRun(RunnableWithThrow, String, boolean)
     */
    public static void runtimeException(RunnableWithThrow<? extends Exception> runnable, String message) {
        simpleExceptionForRun(runnable, message, true);
    }

    /**
     * 异常转换为 ActionExecException
     *
     * @param runnable 带有异常的运行接口
     * @see ExceptionHandler#simpleExceptionForRun(RunnableWithThrow, String, boolean)
     */
    public static <E extends Exception> void runtimeException(RunnableWithThrow<E> runnable) {
        simpleExceptionForRun(runnable, null, true);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier 带有返回值和throw的函数接口
     * @param <T>      supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T ignoreException(SupplierWithThrow<T, ? extends Exception> supplier) {
        return simpleExceptionForSup(supplier, null, null, false);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier     带有返回值和throw的函数接口
     * @param defaultValue supplier 发生错误后的默认返回值
     * @param <T>          supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T ignoreException(SupplierWithThrow<T, ? extends Exception> supplier, T defaultValue) {
        return simpleExceptionForSup(supplier, defaultValue, null, false);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier     带有返回值和throw的函数接口
     * @param defaultValue supplier 发生错误后的默认返回值
     * @param message      发生错误后打印抛出的异常信息
     * @param <T>          supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T ignoreException(SupplierWithThrow<T, ? extends Exception> supplier, T defaultValue, String message) {
        return simpleExceptionForSup(supplier, defaultValue, message, false);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier 带有返回值和throw的函数接口
     * @param <T>      supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T runtimeException(SupplierWithThrow<T, ? extends Exception> supplier) {
        return simpleExceptionForSup(supplier, null, null, true);
    }

    /**
     * 原来需要 catch 的运行时转换为 runtimeException 抛出
     *
     * @param supplier 带有返回值和throw的函数接口
     * @param message  supplier 发生错误后的信息
     * @param <T>      supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T runtimeException(SupplierWithThrow<T, ? extends Exception> supplier, String message) {
        return simpleExceptionForSup(supplier, null, message, true);
    }


    /**
     * 忽略运行的异常
     *
     * @param runnable       指定的函数接口
     * @param message        runnable 发生错误后的信息
     * @param throwException true: runnable 发生错误后抛出运行时异常, false: 仅仅打印日志
     */
    public static void simpleExceptionFor(RunnableWithThrow<? extends Exception> runnable, String message, boolean throwException) {
        simpleExceptionForRun(runnable, message, throwException);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier       带有返回值和throw的函数接口
     * @param defaultValue   throwException为true时, supplier 发生错误后的默认返回值
     * @param message        supplier 发生错误后的信息
     * @param throwException true: supplier 发生错误后抛出运行时异常, false: 仅仅打印日志
     * @param <T>            返回值类型
     * @return supplier 执行成功: supplier的返回值, supplier 执行失败: 返回 defaultValue
     */
    public static <T> T simpleException(SupplierWithThrow<T, ? extends Exception> supplier, T defaultValue, String message, boolean throwException) {
        return simpleExceptionForSup(supplier, defaultValue, message, throwException);
    }

    /**
     * 忽略运行的异常
     *
     * @param runnable 带有异常的运行接口
     * @param message  发生异常报错信息
     * @see ExceptionHandler#simpleExceptionForRun(RunnableWithThrow, String, boolean)
     */
    public static void ignoreExceptionForRun(RunnableWithThrow<? extends Exception> runnable, String message) {
        simpleExceptionForRun(runnable, message, false);
    }

    /**
     * 忽略运行的异常
     *
     * @param runnable 带有异常的运行接口
     * @see ExceptionHandler#simpleExceptionForRun(RunnableWithThrow, String, boolean)
     */
    public static void ignoreExceptionForRun(RunnableWithThrow<? extends Exception> runnable) {
        simpleExceptionForRun(runnable, null, false);
    }

    /**
     * 异常转换为 ActionExecException
     *
     * @param runnable 带有异常的运行接口
     * @param message  发生异常报错信息
     * @see ExceptionHandler#simpleExceptionForRun(RunnableWithThrow, String, boolean)
     */
    public static void runtimeExceptionForRun(RunnableWithThrow<? extends Exception> runnable, String message) {
        simpleExceptionForRun(runnable, message, true);
    }

    /**
     * 异常转换为 ActionExecException
     *
     * @param runnable 带有异常的运行接口
     * @see ExceptionHandler#simpleExceptionForRun(RunnableWithThrow, String, boolean)
     */
    public static void runtimeExceptionForRun(RunnableWithThrow<? extends Exception> runnable) {
        simpleExceptionForRun(runnable, null, true);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier 带有返回值和throw的函数接口
     * @param <T>      supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T ignoreExceptionForSup(SupplierWithThrow<T, ? extends Exception> supplier) {
        return simpleExceptionForSup(supplier, null, null, false);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier     带有返回值和throw的函数接口
     * @param defaultValue supplier 发生错误后的默认返回值
     * @param <T>          supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T ignoreExceptionForSup(SupplierWithThrow<T, ? extends Exception> supplier, T defaultValue) {
        return simpleExceptionForSup(supplier, defaultValue, null, false);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier     带有返回值和throw的函数接口
     * @param defaultValue supplier 发生错误后的默认返回值
     * @param message      supplier 发生错误后的信息
     * @param <T>          supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T ignoreExceptionForSup(SupplierWithThrow<T, ? extends Exception> supplier, T defaultValue, String message) {
        return simpleExceptionForSup(supplier, defaultValue, message, false);
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier 带有返回值和throw的函数接口
     * @param <T>      supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T runtimeExceptionForSup(SupplierWithThrow<T, ? extends Exception> supplier) {
        return simpleExceptionForSup(supplier, null, null, true);
    }

    /**
     * 原来需要 catch 的运行时转换为 runtimeException 抛出
     *
     * @param supplier 带有返回值和throw的函数接口
     * @param message  supplier 发生错误后的信息
     * @param <T>      supplier的提供的返回值类型
     * @return supplier 的返回值
     * @see ExceptionHandler#simpleExceptionForSup(SupplierWithThrow, Object, String, boolean)
     */
    public static <T> T runtimeExceptionForSup(SupplierWithThrow<T, ? extends Exception> supplier, String message) {
        return simpleExceptionForSup(supplier, null, message, true);
    }

    /**
     * 忽略运行的异常
     *
     * @param runnable       指定的函数接口
     * @param message        runnable 发生错误后的信息
     * @param throwException true: runnable 发生错误后抛出运行时异常, false: 仅仅打印日志
     */
    public static void simpleExceptionForRun(RunnableWithThrow<? extends Exception> runnable, String message, boolean throwException) {
        try {
            runnable.run();
        } catch (Exception e) {
            if (message == null) {
                message = "business exception";
            }
            if (throwException) {
                throw new BusinessExecException(message, e);
            } else {
                log.error(message, e);
            }
        }
    }

    /**
     * 忽略运行的异常
     *
     * @param supplier       带有返回值和throw的函数接口
     * @param defaultValue   throwException为true时, supplier 发生错误后的默认返回值
     * @param message        supplier 发生错误后的信息
     * @param throwException true: supplier 发生错误后抛出运行时异常, false: 仅仅打印日志
     * @param <T>            supplier的提供的返回值类型
     * @return supplier 执行成功: supplier的返回值, supplier 执行失败: 返回 defaultValue
     */
    public static <T> T simpleExceptionForSup(SupplierWithThrow<T, ? extends Exception> supplier, T defaultValue, String message, boolean throwException) {
        try {
            return supplier.get();
        } catch (Exception e) {
            if (throwException) {
                throw new BusinessExecException(message, e);
            } else {
                log.warn(message, e);
            }
        }
        return defaultValue;
    }

    /**
     * @param runnableWithThrow 函数表达式(带有异常)
     * @return 若出现异常, 则将异常返回, 否则返回 null
     */
    public static Exception catchThrow(RunnableWithThrow<? extends Exception> runnableWithThrow) {
        try {
            runnableWithThrow.run();
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    /**
     * @param consumerWithThrow 函数表达式(带有异常)
     * @param t                 传入 consumerWithThrow 中的参数
     * @param <T>               传入 consumerWithThrow 中的参数类型
     * @return 若出现异常, 则将异常返回, 否则返回 null
     */
    public static <T> Exception catchThrow(ConsumerWithThrow<T, ? extends Exception> consumerWithThrow, T t) {
        try {
            consumerWithThrow.accept(t);
            return null;
        } catch (Exception e) {
            return e;
        }
    }

}
