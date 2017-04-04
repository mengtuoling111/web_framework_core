package com.study.framework.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by liwei05 on 2016/9/30.
 */
public class JsonUtil {
    static Logger LOGGER = LogManager.getLogger(JsonUtil.class.getName());

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * POJO 转换为JSON
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * Json 转化为POJO
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T formJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOGGER.error("convert json to pojo failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
