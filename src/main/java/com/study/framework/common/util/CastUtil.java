package com.study.framework.common.util;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by liwei05 on 2016/9/20.
 */
public final class CastUtil {

    /**
     * 转为String
     *
     * @param object
     * @return
     */
    public static String castString(Object object) {
        return castString(object, "");
    }

    /**
     * 转为String型（提供默认值）
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return null != obj ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 对象转化为都变了类型
     *
     * @param object
     * @return
     */
    public static double castDouble(Object object) {
        return castDouble(object, (double) 0);
    }

    /**
     * 对象转化为double类型，带有默认值
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object object, Double defaultValue) {
        Double doubleValue = defaultValue;
        if (object != null) {
            String stringValue = castString(object);
            if (StringUtil.isNotEmpty(stringValue)) {
                try {
                    defaultValue = Double.parseDouble(stringValue);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    /**
     * @param object
     * @return
     */
    public static long castLong(Object object) {
        return castLong(object, 0L);
    }

    /**
     * @param object
     * @param defaultValue
     * @return
     */
    public static long castLong(Object object, Long defaultValue) {
        Long longValue = defaultValue;
        if (object != null) {
            String stringValue = castString(object);
            if (StringUtils.isNotEmpty(stringValue)) {
                try {
                    longValue = Long.parseLong(stringValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转化为Int
     * @param object
     * @return
     */
    public static int castInt(Object object) {
        return castInt(object, 0);
    }

    /**
     *转化为Int带有默认值
     * @param object
     * @param defaultValue
     * @return
     */
    public static int castInt(Object object, int defaultValue) {
        Integer intValue = defaultValue;
        if(object != null) {
            String stringValue = castString(object);
            if(StringUtil.isEmpty(stringValue)) {
                try {
                    intValue = Integer.parseInt(stringValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     *
     * @param object
     * @param defaultvalue
     * @return
     */
    public static boolean castBoolean(Object object, boolean defaultvalue) {
        Boolean booleanValue = defaultvalue;
        if(object != null) {
            booleanValue = Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }

    /**
     *
     * @param object
     * @return
     */
    public static boolean castBoolean(Object object) {
        return castBoolean(object, false);
    }
}
