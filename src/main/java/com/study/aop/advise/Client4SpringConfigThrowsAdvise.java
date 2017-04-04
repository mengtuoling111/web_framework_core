package com.study.aop.advise;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liwei05 on 2016/10/25.
 */
public class Client4SpringConfigThrowsAdvise {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring1.xml");
        HelloEx hello = (HelloEx)applicationContext.getBean("helloProxy");
        hello.say("spring throws Exception xml config");
    }

}
