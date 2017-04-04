package com.study.aop.aspectJ.annotion;

import org.springframework.stereotype.Component;

/**
 * Created by liwei05 on 2016/10/26.
 */
@Component
public class GreetingImpl implements Hello {

    @Tag
    @Override
    public void say(String name) {
        System.out.println("say....." + name);
    }

    public void goodMorning(String name) {
        System.out.println("goodMorning ....." + name);
    }

    public void goodNight(String name) {
        System.out.println("goodAfterNight ....." + name);
    }
}
