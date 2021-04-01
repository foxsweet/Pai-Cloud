package com.pai.common.core.utils;

/**
 * @创建人 dmm
 */
public class StringUtils {

    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isNotEmpty(str);
    }

    /**
     * 判断对象不为空
     *
     * @param object
     * @return
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

}
