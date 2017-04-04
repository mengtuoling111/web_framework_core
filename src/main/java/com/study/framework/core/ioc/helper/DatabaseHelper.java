package com.study.framework.core.ioc.helper;

import com.study.framework.common.util.CollectionUtil;
import com.study.framework.common.util.PropsUtil;
import com.study.app.step3.model.Customer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liwei05 on 2016/9/24.
 */
public class DatabaseHelper {

    static Logger LOGGER = LogManager.getLogger(DatabaseHelper.class.getName());

    public static final QueryRunner QUERY_RUNNER = new QueryRunner();
    public static final ThreadLocal<Connection> CONNECTION_LOLDER = new ThreadLocal<>();
    public static final BasicDataSource DATA_SOURCE;

    private static final String URL;
    private static final String DRIVER;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setPassword(PASSWORD);
        DATA_SOURCE.setUsername(USERNAME);
    }

    /**
     * 回滚事物
     */
    public static void rollbackTransaction() {
        Connection conn = getConnection();
        if (null != conn) {
            try {
                conn.rollback();
                conn.close();
                LOGGER.debug("事物执行失败，事物回滚成功");
            } catch (SQLException e) {
                LOGGER.error("rollback trancation failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_LOLDER.remove();
            }
        }
    }

    /**
     * 开启事物
     */
    public static void beginTranscation() {
        Connection conn = getConnection();
        try {
            LOGGER.debug("开启事物");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("begin trancation failure", e);
            throw new RuntimeException(e);
        } finally {
            CONNECTION_LOLDER.set(conn);
        }
    }

    /**
     * 提交事物
     * @return
     */
    public static void commitTransaction() {
        Connection conn = CONNECTION_LOLDER.get();
        if(conn != null) {
            try {
                conn.commit();
                conn.close();
                LOGGER.debug("事物提交完成");
            } catch (SQLException e) {
                LOGGER.error("commit transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_LOLDER.remove();
            }
        }
    }


    /**
     *  获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = CONNECTION_LOLDER.get();
        if (null == conn) {
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
            } finally {
                CONNECTION_LOLDER.set(conn);
            }
        }
        return conn;
    }

    /**
     * @param conn
     */
    public static void closeConnection() {
        Connection conn = CONNECTION_LOLDER.get();
        if (conn != null) {
            try {
                conn.close();
                LOGGER.debug("关闭事物结束。");
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_LOLDER.remove();
            }
        }
    }

    /**
     * 查询实体列表
     *
     * @param entityClass
     * @param sql
     * @param param
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... param) {
        List<T> entityList;
        try {
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), param);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        }
        return entityList;
    }

    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... param) {
        T entity;
        try {
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), param);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * 执行查询语句
     *
     * @param sql
     * @param param
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... param) {
        List<Map<String, Object>> result;
        try {
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), param);
        } catch (SQLException e) {
            LOGGER.error("execute query failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 执行更新语句（包括 update， insert, delete）
     */
    public static int executeUpdate(String sql, Object... param) {
        int rows = 0;
        try {
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql, param);
        } catch (SQLException e) {
            LOGGER.error("execute update failure", e);
        }
        return rows;
    }

    /**
     * 插入实体类
     *
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not insert Entity: field is Empty");
            return false;
        }
        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder(" (");
        StringBuilder values = new StringBuilder(" (");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(","), columns.length(), ")");
        values.replace(values.lastIndexOf(","), values.length(), ")");
        sql += columns + "VALUES" + values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 更新操作
     *
     * @param entityClass
     * @param fieldMap
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass, Map<String, Object> fieldMap, Long id) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not update entity: fieldMap is empty");
            return false;
        }
        String sql = " update " + entityClass.getSimpleName().toLowerCase() + " set ";
        StringBuilder colums = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            colums.append(fieldName).append("= ?, ");
        }
        sql += colums.substring(0, colums.lastIndexOf(",")) + " where id = ?";
        List<Object> paramList = new ArrayList<>(fieldMap.values());
        paramList.add(id);
        Object[] param = paramList.toArray();
        return executeUpdate(sql, param) == 1;
    }

    /**
     * 删除操作
     *
     * @param entityClass
     * @param fieldMap
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> entityClass,  Long id) {
        if (null == id) {
            LOGGER.error("can not delete entity: id is empty");
            return false;
        }
        String sql = " delete from " + getTableName(Customer.class) + " where id = ?";
        return executeUpdate(sql, id) == 1;
    }

    /**
     * 获得类名字的简写
     * @param entityClass
     * @return
     */
    private static String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName().toLowerCase();
    }

    /**
     * 初始化sql脚本，目前用于单元测试
     * @param filename
     */
    public static void executeSqlFile(String filename) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String sql;
            while( (sql = reader.readLine()) != null) {
                DatabaseHelper.executeUpdate(sql);
            }
        } catch (IOException e) {
            LOGGER.error("execute sql file failer", e);
        }
    }

}
