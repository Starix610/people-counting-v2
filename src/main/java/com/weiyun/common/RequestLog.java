// Copyright (C) 2020 Meituan
// All rights reserved
package com.weiyun.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

/**
 * @author shiwenjie
 * @created 2020/6/16 2:46 下午
 **/
@Data
@AllArgsConstructor
public class RequestLog{

    private String url;

    private String method;

    private String ip;

    private String classMethod;

    private Object[] parameters;

    @Override
    public String toString() {
        return "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", parameters=" + Arrays.toString(parameters);
    }
}