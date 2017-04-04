package com.study.aop.aspectJ.annotion;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by liwei05 on 2016/10/28.
 */
@Aspect
@Component
public class GreetingAspectAnnotation {

    @Around("@annotation(aop.aspectJ.annotion.Tag)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }

    private void before() {
        System.out.println("Before Spring+AspectJ+Annotation");
    }

    private void after() {
        System.out.println("After Spring+AspectJ+Annotation");
    }
}
