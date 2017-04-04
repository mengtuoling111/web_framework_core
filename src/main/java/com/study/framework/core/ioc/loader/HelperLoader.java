package com.study.framework.core.ioc.loader;

import com.study.framework.core.ioc.helper.*;
import com.study.framework.core.util.ClassUtil;

/**
 * Created by liwei05 on 2016/9/30.
 */
public class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                Beanhelper.class,
                AopHelper.class,
                IOCHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
