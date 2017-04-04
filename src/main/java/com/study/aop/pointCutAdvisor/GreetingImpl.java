package com.study.aop.pointCutAdvisor;

import org.springframework.stereotype.Component;

/**
 * Created by liwei05 on 2016/10/26.
 */
@Component
public class GreetingImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("say....." + name);
    }

    public void goodMorning(String name) {
        System.out.println("goodMorning ....." + name);
    }

    public void goodAfterNight(String name) {
        System.out.println("goodAfterNight ....." + name);
    }
}
