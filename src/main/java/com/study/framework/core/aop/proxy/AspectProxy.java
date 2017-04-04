package com.study.framework.core.aop.proxy;

import com.study.framework.core.aop.ProxyWei;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by liwei05 on 2016/11/1.
 */
public abstract class AspectProxy implements Proxy {

    static Logger logger = LogManager.getLogger(AspectProxy.class.getName());

    @Override
    public Object doProxy(ProxyWei proxyWei) throws Throwable {
        Object result = null;
        Class<?> cls = proxyWei.getTargetClass();
        Method method = proxyWei.getTargetMethod();
        Object[] params = proxyWei.getMethodParam();
        begin();

        try {
            if(intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyWei.doProxyWei();
                after(cls, method, params, result);
            } else {
                result = proxyWei.doProxyWei();
            }
        } catch (Exception e) {
            logger.error("proxy failure", e);
            error(cls, method, params, e);
        } finally {
            end();
        }
        return result;
    }

    public void begin() {

    }

    public boolean intercept(Class<?> cls, Method method, Object[] param) throws Throwable {
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable{

    }

    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable{

    }

    public void error(Class<?> cls, Method method, Object[] params, Throwable e) throws Throwable{

    }

    public void end() {

    }
}
