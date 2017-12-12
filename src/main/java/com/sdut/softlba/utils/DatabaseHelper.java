/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdut.softlba.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huanlu
 */
public final class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();
    private static final QueryRunner QUERY_RUNNER;
    //private static final BasicDatasource DATA_SOURCE;
    private static final String URL;
    private static final String DRIVER;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        QUERY_RUNNER = new QueryRunner();
        Properties conf = PropsUtil.loadProperties("database.properties");
        URL = conf.getProperty("database.url");
        DRIVER = conf.getProperty("database.dirver");
        USERNAME = conf.getProperty("database.usrename");
        PASSWORD = conf.getProperty("database.passowrd");

        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            LOGGER.error("cannot load jdbc driver!", e);
        }
    }

    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException ex) {
                LOGGER.error("get connection faile!");
                throw new RuntimeException(ex);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                LOGGER.error("Close Connection failure", ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public static <T> List<T> queryEntityList(Class<T> entitClass, String sql, Object... params) {
        List<T> entityList;
        try {
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<>(entitClass), params);
        } catch (Exception e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entityList;
    }

    private static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result;
        try {
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        } catch (SQLException ex) {
            LOGGER.error("execute query is failure!", ex);
            throw new RuntimeException(ex);
        }
        return result;
    }
    
    public static int executeUpdate(String sql,Object...params){
       int rows = 0;
        try {
            Connection conn =getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (Exception e) {
            LOGGER.error("execute update is failure!",e);
            throw new RuntimeException(e);
        }
        return rows;
    }
}
