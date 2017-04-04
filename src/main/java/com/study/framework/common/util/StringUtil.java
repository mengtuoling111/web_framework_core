package com.study.framework.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by liwei05 on 2016/9/21.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null != str) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * 分割字符串，返回数组
     *
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] splitsString(final String str, final String separatorChars) {
        return StringUtils.split(str, separatorChars);

    }
}
