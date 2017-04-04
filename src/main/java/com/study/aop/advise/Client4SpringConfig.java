package com.study.aop.advise;

import com.study.aop.cglibProxy.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liwei05 on 2016/10/25.
 */
public class Client4SpringConfig {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Hello hello = (Hello)applicationContext.getBean("helloProxy");
        hello.say("spring xml config");
    }

}
