package com.icongtai.geo.utils;

import com.icongtai.geo.model.Constance;

/**
 * 字符串操作相关工具类
 */
public class StringUtil {
    /**
     * 看字符串是否非空
     * @param str 非空返回true
     * @return true:非空 false:空
     */
    public static boolean isNotEmptyOrNull(Object str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return true;
    }

    /**
     * 看字符串是否为空
     * @param str 非空返回true
     * @return true:空 false:非空
     */
    public static boolean isEmptyOrNull(Object str) {
        return !isNotEmptyOrNull(str);
    }

    /**
     * 验证ranges 范围格式的数据，必须是数字，而且用中横线“-”分隔
     * @return 格式正确true 格式不正确 false
     */
    public static boolean validateRangeVlaues(String rangeValues) {
        if (StringUtil.isNotEmptyOrNull(rangeValues)) {
            String[] ranges = rangeValues.split(Constance.SPLIT_SEARCH_RANGE);
            if (ranges.length != 2) {
                return false;
            }
            for (String range : ranges) {
                if (!NumberUtil.isNumeric(range)) {
                    return false;
                }
            }
        }
        return true;
    }
}
