package com.study.aop.cglibProxy;


import org.springframework.stereotype.Component;

/**
 * Created by liwei05 on 2016/10/20.
 */
@Component
public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Say ..." + name);
    }
}
