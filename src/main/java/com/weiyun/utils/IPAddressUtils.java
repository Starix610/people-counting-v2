package com.weiyun.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tobu
 * @date 2019-08-25 12:35
 */
public class IPAddressUtils {

    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getIPAddr(HttpServletRequest request){

        String ip=request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("X-Real-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }

        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if (ip != null && ip.length() > 15 && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }
}
