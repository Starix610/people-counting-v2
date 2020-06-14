package com.weiyun.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码格式验证工具类
 * @author Tobu
 * @date 2019/7/20 23:36
 */
public class PhoneValidateUtils {

    public static boolean validatePhone(String telephone){
        String regex = "^1([3-9])[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(telephone);
        return m.matches();
    }
}
