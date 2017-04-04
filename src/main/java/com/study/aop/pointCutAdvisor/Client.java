package com.study.aop.pointCutAdvisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liwei05 on 2016/10/27.
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring3.xml");
        GreetingImpl greet = (GreetingImpl)applicationContext.getBean("greetingProxy");
        greet.goodAfterNight("Advisor, 不是切点没有增强");
        System.out.println("-----------------------------------");
        greet.goodMorning("Advisor， 是切点增强");
        System.out.println("-----------------------------------");
        greet.say("Advisor，是切点增强");
    }
}
