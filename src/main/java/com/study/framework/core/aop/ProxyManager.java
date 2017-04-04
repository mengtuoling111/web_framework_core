package com.study.framework.core.aop;

import com.study.framework.core.aop.proxy.Proxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理器，创建所有的代理对象
 * Created by liwei05 on 2016/11/1.
 */
public class ProxyManager {

    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyWei(targetClass, targetObject, method, methodProxy, objects, proxyList).doProxyWei();
            }
        });
    }
}
