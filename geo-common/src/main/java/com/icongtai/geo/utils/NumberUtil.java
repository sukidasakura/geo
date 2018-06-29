package com.icongtai.geo.utils;


/**
 * 数字相关操作工具类
 */
public class NumberUtil {
    public static boolean isNumeric(String str) {
        if (StringUtil.isEmptyOrNull(str)) {
            return false;
        }
        return str.matches("[0-9]*|[0-9]*.[0-9]*");
    }
}

