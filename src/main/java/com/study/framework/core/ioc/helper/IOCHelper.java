package com.study.framework.core.ioc.helper;

import com.study.framework.common.util.ArrayUtil;
import com.study.framework.common.util.CollectionUtil;
import com.study.framework.core.annotation.Inject;
import com.study.framework.core.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by liwei05 on 2016/9/29.
 */
public class IOCHelper {
    static {
        //获取所有的Bean类与Bean实例之间的映射关系（简称 Bean Map）
        Map<Class<?>, Object> beanMap = Beanhelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)) {
            for(Map.Entry<Class<?>, Object> beanEntry: beanMap.entrySet()) {
                //从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bean类定义的所有成员变量（简称 Bean Field）
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)) {
                    //遍历 Bean Field
                    for(Field beanField : beanFields) {
                        //判断当前Bean Field 是否带有Inject注解
                        if(beanField.isAnnotationPresent(Inject.class)) {
                            //在 Bean Map中获取Bean field对应的实例。
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
