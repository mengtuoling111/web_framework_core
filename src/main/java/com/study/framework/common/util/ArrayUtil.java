package com.study.framework.common.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by liwei05 on 2016/9/29.
 */
public final class ArrayUtil {

    /**
     * 判断数组是否为空
     *
     * @param arrary
     * @return
     */
    public static boolean isNotEmpty(Object[] arrary) {
        return !ArrayUtils.isEmpty(arrary);
    }

    /**
     * 判断数组是否为空
     *
     * @param arrary
     * @return
     */
    public static boolean isEmpty(Object[] arrary) {
        return ArrayUtils.isEmpty(arrary);
    }
}
