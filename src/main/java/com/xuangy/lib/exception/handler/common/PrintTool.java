package com.xuangy.lib.exception.handler.common;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 异常打印工具类
 * @author: xuanguangyao
 * @email: wtopps111@gmail.com
 * @date: 2021/12/20 14:36
 * @see java.util.function.Consumer
 */

@Slf4j
public class PrintTool {

    private static final String DIRECTOR_STRING = " ==> ";

    enum Level {
        OFF(Integer.MAX_VALUE), FATAL(50000), ERROR(40000), WARN(30000), INFO(30000), DEBUG(30000);

        private final int val;

        Level(int val) {
            this.val = val;
        }

        public int toInt() {
            return val;
        }

    }

    /**
     * 1: log, 0: 控制台
     */
    @Setter
    private static boolean logFlag;

    @Setter
    private static Level logLevel = Level.DEBUG;

    public static void printDebug(String s, Object... objects) {
        print(Level.DEBUG, s, objects);
    }

    public static void printError(String s, Object... objects) {
        print(Level.ERROR, s, objects);
    }

    public static void printWarning(String s, Object... objects) {
        print(Level.WARN, s, objects);
    }

    public static void printInfo(String s, Object... objects) {
        print(Level.INFO, s, objects);
    }

    public static void printSuccess(String s, Object... objects) {
        print(Level.INFO, s, objects);
    }

    @SuppressWarnings("java:S106")
    public static void print(Level level, String s, Object... objects) {
        switch (level) {
            case DEBUG:
                if (logLevel.toInt() <= Level.DEBUG.toInt()) {
                    if (logFlag) {
                        log.debug(s, objects);
                    } else {
                        System.out.println(level.name() + DIRECTOR_STRING + format(s, objects));
                    }
                }
                break;
            case INFO:
                if (logLevel.toInt() <= Level.INFO.toInt()) {
                    if (logFlag) {
                        log.info(s, objects);
                    } else {
                        System.out.println(level.name() + DIRECTOR_STRING + format(s, objects));
                    }
                }
                break;
            case WARN:
                if (logLevel.toInt() <= Level.WARN.toInt()) {
                    if (logFlag) {
                        log.warn(s, objects);
                    } else {
                        System.out.println(level.name() + DIRECTOR_STRING + format(s, objects));
                    }
                }
                break;
            case ERROR:
                if (logLevel.toInt() <= Level.ERROR.toInt()) {
                    if (logFlag) {
                        log.error(s, objects);
                    } else {
                        System.out.println(level.name() + DIRECTOR_STRING + format(s, objects));
                    }
                }
                break;
            default:
        }
    }

    /**
     * 将String中的{}转换为参数中指定的值
     * <p>字符串中的 {} 和 params 中的参数由左至右一一匹配, 匹配上则替换, 匹配不上则不进行替换</p>
     * <p>params中对象为null, 则转换为 EMPTY_STRING </p>
     *
     * @param str 待转换的字符串
     * @param params 参数
     * @return 转换后的字符串
     */
    public static String format(String str, Object... params) {
        if (params == null || params.length == 0) {
            return str;
        }
        int from = 0;
        int end;
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            end = str.indexOf("{}", from);
            if (end < 0) {
                break;
            }
            sb.append(str, from, end);
            if (param != null) {
                sb.append(param);
            }
            from = end + 2;
        }
        return sb.toString();
    }

}
