package com.study.aop.aspectJ.annotion.Introduction;

import com.study.aop.aspectJ.annotion.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liwei05 on 2016/10/31.
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring7.xml");
        Hello greetingImpl = (Hello)applicationContext.getBean("greetingIntroductionImpl");
        //双重增强的sqyHello方法..g
        System.out.println("-----------------------------------");
        greetingImpl.say("helllo");
        System.out.println("-----------------------------------");
        Apology apology = (Apology) greetingImpl;
        apology.sqySorry("Introduction");
        System.out.println("-----------------------------------");
    }
}
