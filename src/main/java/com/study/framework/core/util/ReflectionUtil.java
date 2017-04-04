package com.study.framework.core.util;

import com.study.framework.core.ioc.bean.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liwei05 on 2016/9/28.
 */
public class ReflectionUtil {
    static Logger LOGGER = LogManager.getLogger(ReflectionUtil.class.getName());

    /**
     * 创建类的实列
     *
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 方法反射调用
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Param args) {
        int size = args.getParamNo();
        Object result = null;
        try {
            if (size == 0) {
                result = method.invoke(obj);
            } else {
                result = method.invoke(obj, args);
            }
        } catch (Exception e) {
            LOGGER.error("invork method failure", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result = null;
        try {
            method.setAccessible(true);
            if (args.length == 0) {
                result = method.invoke(obj);
            } else {
                result = method.invoke(obj, args);
            }
        } catch (Exception e) {
            LOGGER.error("invork method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     *
     * @param obj   类的实例
     * @param field 类的属性
     * @param value 类的属性所对应的实例
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
    }
}
