package com.study.app.step3.service;

import com.study.app.step3.model.Customer;
import com.study.framework.core.annotation.Service;
import com.study.framework.core.annotation.Transaction;
import com.study.framework.core.ioc.helper.DatabaseHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by liwei05 on 2016/9/20.
 */
@Service
public class CustomerService {

    static Logger logger = LogManager.getLogger(CustomerService.class.getName());


    /**
     * 获取客户列表
     *
     * @return
     */
    @Transaction
    public List<Customer> getCustomerList() {
        String sql = "select * from customer";
        return DatabaseHelper.queryEntityList(Customer.class,sql);
    }

    /**
     * 获取用户信息
     *
     * @param keyWord
     * @return
     */
    @Transaction
    public Customer getCustomer(Long id) {
        String sql = "select * from customer where id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    /**
     * 创建客户
     *
     * @param id
     * @param fileMap
     * @return
     */
    @Transaction
    public Boolean createCustomer(Map<String, Object> fileMap) {
        return DatabaseHelper.insertEntity(Customer.class, fileMap);
    }

    @Transaction
    public Boolean updateCustomer(long id, Map<String, Object> fileMap) {
        return DatabaseHelper.updateEntity(Customer.class, fileMap, id);
    }

    /**
     * 删除客户
     *
     * @return
     */
    @Transaction
    public Boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);

    }
}
