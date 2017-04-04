package com.study.framework.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by liwei05 on 2016/9/30.
 */
public class StreamUtil {
    static Logger logger = LogManager.getLogger(StreamUtil.class.getName());

    /**
     * 从输入流中获取字符
     */
    public static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            logger.error("get string failure", e);
           throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
