package com.weiyun.netty.enums;

/**
 * @author Starix
 * @date 2021/3/6 11:28
 */
public enum WebsocetAction {

    CONNECT(1, "第一次(或重连)初始化连接"),
    CHANGE_AREA(2, "更换监测区域"),
    KEEPALIVE(4, "客户端心跳信息");

    private  int type;
    private  String content;

    WebsocetAction(Integer type, String content){
        this.type = type;
        this.content = content;
    }

}
