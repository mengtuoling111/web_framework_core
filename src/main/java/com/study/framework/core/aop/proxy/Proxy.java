package com.study.framework.core.aop.proxy;

import com.study.framework.core.aop.ProxyWei;

/**
 * Created by liwei05 on 2016/11/1.
 */
public interface Proxy {
    /**
     * 执行链式处理
     */
    Object doProxy(ProxyWei proxyWei) throws Throwable;
}
