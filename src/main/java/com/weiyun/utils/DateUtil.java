package com.weiyun.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Starix
 * @date 2020-07-21 2:13
 */
public class DateUtil {


    public static String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static String getDateTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

}
