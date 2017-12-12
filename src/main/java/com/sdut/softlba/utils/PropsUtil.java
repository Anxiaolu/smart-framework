/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdut.softlba.utils;

import com.sdut.softlba.utils.base.Castutil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huanlu
 */
public final class PropsUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
    
    /**
     * 记载属性文件
     * @param fileName
     * @return 
     */
    public static Properties loadProperties(String fileName){
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + "file is not found exception!");
            }
            props = new Properties();
            props.load(is);
        } catch (Exception e) {
            LOGGER.error("load properties file failure",e);
        }finally{
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(PropsUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return props;
    }
    
    /**
     * 获取字符型属性（默认值为空字符串）
     * @param props
     * @param key
     * @return 
     */
    public static String getString(Properties props,String key){
        return getString(props, key,"");
    }
    
    /**
     * 获取字符型数据（可制定默认值）
     * @param props
     * @param key
     * @param defaultString
     * @return 
     */
    public static String getString(Properties props,String key,String defaultString){
        String value = defaultString;
        if (props.contains(key)) {
            value = props.getProperty(key);
        }
        return value;
    }
    
    /**
     * 获取整数型数据（默认值为0）
     * @param props
     * @param key
     * @return 
     */
    public static int getInt(Properties props,String key){
        return getInt(props, key, 0);
    }
    
    /**
     * 获取整数型数据(可制定默认值)
     * @param props
     * @param key
     * @param defaultValue
     * @return 
     */
    public static int getInt(Properties props,String key,int defaultValue){
        int value = defaultValue;
        if(props.contains(key)){
            value = Integer.parseInt(props.getProperty(key));
        }
        return value;
    }
    
    /**
     * 获取布尔型数据（默认值为false）
     * @param props
     * @param key
     * @return 
     */
    public static boolean getDouble(Properties props,String key){
        return getBoolean(props, key, false);
    }
    
    /**
     * 获取布尔型数据(可制定默认值)
     * @param props
     * @param key
     * @param defaultValue
     * @return 
     */
    public static boolean getBoolean(Properties props, String key, Boolean defaultValue) {
        Boolean value = defaultValue;
        if (props.contains(key)) {
            value = Castutil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
