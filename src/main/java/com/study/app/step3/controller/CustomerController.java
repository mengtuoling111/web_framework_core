package com.study.app.step3.controller;

import com.study.app.step3.model.Customer;
import com.study.app.step3.service.CustomerService;
import com.study.framework.core.annotation.Action;
import com.study.framework.core.annotation.Aspect;
import com.study.framework.core.annotation.Controller;
import com.study.framework.core.annotation.Inject;
import com.study.framework.core.ioc.bean.Data;
import com.study.framework.core.ioc.bean.Param;
import com.study.framework.core.ioc.bean.View;

import java.util.List;
import java.util.Map;

/**
 * 处理客户管理相关请求
 * Created by liwei05 on 2016/10/8.
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * 进入客户列表 界面
     */
    @Action("get:/customer")
    public View index() {
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    /**
     * 显示客户基本信息
     */
    @Action("get:/customer_show")
    public View show(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer", customer);
    }

    /**
     * 处理创建客户 请求
     *
     * @return
     */
    @Action("get:/customer_create")
    public View create(Param param) {
        return new View("customer_create.jsp");
    }

    /**
     * 进入 编辑客户 界面
     *
     * @param param
     * @return
     */
    @Action("get:/customer_edit")
    public View edit(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_edit.jsp").addModel("customer", customer);
    }

    /**
     * 处理 编辑客户 请求
     *
     * @param param
     * @return
     */
    @Action("put:/customer_edit")
    public Data editSubmit(Param param) {
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getMap();
        boolean result = customerService.updateCustomer(id, fieldMap);
        return new Data(result);
    }

    /**
     * 处理删除客户请求
     *
     * @param param
     * @return
     */
    @Action("delete:/customer_delete")
    public Data delete(Param param) {
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }

    @Action("get:/customer_delete")
    public Data deleteWrapGet(Param param) {
        return delete(param);
    }

}
