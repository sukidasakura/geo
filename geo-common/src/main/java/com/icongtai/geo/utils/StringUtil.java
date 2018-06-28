package com.icongtai.geo.utils;

/**
 * 字符串操作相关工具类
 */
public class StringUtil {
    public static boolean isNotEmptyOrNull(Object str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return true;
    }
}
