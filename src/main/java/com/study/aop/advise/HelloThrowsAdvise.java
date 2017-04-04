package com.study.aop.advise;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by liwei05 on 2016/10/25.
 */
@Component
public class HelloThrowsAdvise implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception e){
        System.out.println("--------------抛出异常--------------");
        System.out.println("Target Class: "+target.getClass().getName());
        System.out.println("Method name: "+method.getName());
        System.out.println("Exception message: "+e.getMessage());
        System.out.println("-----------------------------------");
    }
}
