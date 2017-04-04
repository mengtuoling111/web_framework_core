package com.study.framework.core.ioc.bean;

import com.study.framework.common.util.CastUtil;

import java.util.Map;

/**
 * Created by liwei05 on 2016/9/30.
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取Long类型的数值
     *
     * @param name
     * @return
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getMap() {
        return paramMap;
    }

    public int getParamNo() {
        return paramMap.size();
    }
}
