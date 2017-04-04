package com.study.aop.advise;

import com.study.aop.cglibProxy.Hello;
import com.study.aop.cglibProxy.HelloImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by liwei05 on 2016/10/25.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HelloImpl());
        //proxyFactory.addAdvice(new GreetingAfterAdvise()); //后置增强
        //proxyFactory.addAdvice(new GreetingBeforeAdvise());//前置增强
        //proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvise()); //间接实现环绕增强
        proxyFactory.addAdvice(new GreetingAroundAdvise());
        Hello hello = (Hello) proxyFactory.getProxy();
        hello.say("Hello advise");
    }
}
