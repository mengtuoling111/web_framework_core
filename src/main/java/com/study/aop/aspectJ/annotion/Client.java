package com.study.aop.aspectJ.annotion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * Created by liwei05 on 2016/10/27.
 */
public class Client {
    public static void main(String[] args) {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring6.xml");
            GreetingImpl greetingImpl = (GreetingImpl)applicationContext.getBean("greetingImpl", GreetingImpl.class);
            //双重增强的sqyHello方法..g
            System.out.println("-----------------------------------");
            greetingImpl.say("AspectJ");
            System.out.println("-----------------------------------");
            greetingImpl.goodMorning("AspectJ");
            System.out.println("-----------------------------------");
            greetingImpl.goodNight("AspectJ");
            System.out.println("-----------------------------------");
        }
}
