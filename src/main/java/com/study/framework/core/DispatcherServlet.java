package com.study.framework.core;

import com.study.framework.common.util.ArrayUtil;
import com.study.framework.common.util.StringUtil;
import com.study.framework.core.ioc.bean.Data;
import com.study.framework.core.ioc.bean.Param;
import com.study.framework.core.ioc.bean.View;
import com.study.framework.core.ioc.handler.Handler;
import com.study.framework.core.ioc.helper.Beanhelper;
import com.study.framework.core.ioc.helper.ConfigHelper;
import com.study.framework.core.ioc.helper.ControllerHelper;
import com.study.framework.core.ioc.loader.HelperLoader;
import com.study.framework.core.util.CodeUtil;
import com.study.framework.core.util.JsonUtil;
import com.study.framework.core.util.ReflectionUtil;
import com.study.framework.core.util.StreamUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwei05 on 2016/9/30.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    static Logger LOGGER = LogManager.getLogger(DispatcherServlet.class.getName());

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关的Helper类
        HelperLoader.init();
        //获取ServletContext对象（用于注册Servlet）
        ServletContext servletContext = servletConfig.getServletContext();
        //注册去处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
}


    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求方法与请求路径
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();
        //获取Action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            //获取Controller类以及Bean的实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = Beanhelper.getBean(controllerClass);
            //创建请求参数
            //get请求参数
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramnames = request.getParameterNames();
            while (paramnames.hasMoreElements()) {
                String paramName = paramnames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            //post请求参数
            String body = CodeUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            if (StringUtil.isNotEmpty(body)) {
                String[] params = StringUtil.splitsString(body, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] arrary = StringUtil.splitsString(param, "=");
                        if (ArrayUtil.isNotEmpty(arrary) && arrary.length == 2) {
                            String paramName = arrary[0];
                            String paramValue = arrary[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            //调用Action方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            //处理Action返回值
            if (result instanceof View) {
                //返回JSP页面
                View view = (View) result;
                String path = view.getPath();
                if (StringUtil.isNotEmpty(path)) {
                    if (path.startsWith("/")) {
                        response.sendRedirect(request.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
                        if (LOGGER.isDebugEnabled()) LOGGER.debug("redirect to " + ConfigHelper.getAppJspPath() + path);
                       request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
                    }
                }
            } else if (result instanceof Data) {
                //返回JSON数据
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String JSON = JsonUtil.toJson(model);
                    writer.write(JSON);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
