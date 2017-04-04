package com.study.framework.core.ioc.bean;

/**
 * Created by liwei05 on 2016/9/30.
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
