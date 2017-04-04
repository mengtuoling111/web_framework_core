package com.study.aop.IntroDuctionAdvice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liwei05 on 2016/10/26.
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring2.xml");
        HelloIntroduction helloIntroduction = (HelloIntroduction)context.getBean("introductionProxy");
        helloIntroduction.say("introducton");
        Apology apology = (Apology)helloIntroduction;
        apology.saySorry("sorry");
    }
}
