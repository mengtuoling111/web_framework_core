package com.study.aop.aspectJ.annotion.Introduction;

import org.springframework.stereotype.Component;

/**
 * Created by liwei05 on 2016/10/31.
 */
@Component
public class ApologyImpl implements Apology {
    @Override
    public void sqySorry(String name) {
        System.out.println("Sorry" + name);
    }
}
