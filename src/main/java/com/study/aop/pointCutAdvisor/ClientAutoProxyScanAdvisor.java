package com.study.aop.pointCutAdvisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liwei05 on 2016/10/27.
 */
public class ClientAutoProxyScanAdvisor {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring5.xml");
        GreetingImpl greetingImpl = applicationContext.getBean("greetingImpl", GreetingImpl.class);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        greetingImpl.goodAfterNight("Advisor, 是切点增强");
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        greetingImpl.goodMorning("Advisor， 是切点增强");
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        greetingImpl.say("Advisor，是切点增强");
        System.out.println("+++++++++++++++++++++++++++++++++++++");
    }
}
