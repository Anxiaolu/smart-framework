/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdut.softlba.utils.base;

/**
 *
 * @author huanlu
 */
public class Castutil {
    
    public static String castString(Object obj){
        return castString(obj);
    }
    
    public static String castString(Object obj,String defaultString){
        return obj != null ? String.valueOf(obj) : defaultString;
    }
    
    public static double castDouble(Object obj){
        return castDouble(obj,0);
    }
    
    public static double castDouble(Object obj,double defaultValue){
        double value = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if (!strValue.isEmpty()) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (Exception e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }
    
    public static long castLong(Object obj){
        return castLong(obj,0);
    }
    
    public static long castLong(Object obj,long defaultValue){
        long value = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if (!strValue.isEmpty()) {
                try {
                    value = Long.parseLong(strValue);
                } catch (Exception e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }
    
    public static int castInt(Object obj){
        return castInt(obj,0);
    }
    
    public static int castInt(Object obj,int defaultValue){
        int value = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if (!strValue.isEmpty()) {
                try {
                    value = Integer.parseInt(strValue);
                } catch (Exception e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }
    
    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }
    
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean value = defaultValue;
        if(obj != null){
            value = Boolean.parseBoolean(castString(obj));
        }
        return value;
    }
}
