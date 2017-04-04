package com.study.framework.core.ioc.helper;

import com.study.framework.common.util.ArrayUtil;
import com.study.framework.common.util.CollectionUtil;
import com.study.framework.core.annotation.Action;
import com.study.framework.core.ioc.bean.Request;
import com.study.framework.core.ioc.handler.Handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by liwei05 on 2016/9/30.
 */
public class ControllerHelper {

    /**
     * 用于存储请求与处理器的映射关系
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        //获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        //遍历这些Controller类
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                //获取Controller类中的方法。
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    //遍历这些Controller中的方法
                    for (Method method : methods) {
                        //判断当前方法是否有Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            //从Action注解中获取URL映射规则
                            Action action = method.getDeclaredAnnotation(Action.class);
                            String mapping = action.value();
                            //验证URL映射规则
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] arrary = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(arrary) && arrary.length == 2) {
                                    //获取请求方法与请求路径
                                    String requestMethod = arrary[0];
                                    String requestPath = arrary[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     *
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
