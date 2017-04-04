package com.study.app.step1;

import com.study.framework.core.util.ClassUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by liwei05 on 2016/9/27.
 */
@WebServlet("/test")
public class ClassloaderTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "com.study.framework.common.helper";
        Set<Class<?>> set = ClassUtil.getClassSet(url);
        System.out.println(set);
    }
}