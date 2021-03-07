// Copyright (C) 2020 Meituan
// All rights reserved
package com.weiyun.netty.utils;

import com.weiyun.netty.handler.WebSocketHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * Netty WebSokcet工具类
 * @author shiwenjie
 * @created 2020/6/11 6:05 下午
 **/
public class WebSocketUtil {

    /**
     * 发送消息给所有已连接的客户端
     * @param msg
     */
    public static void sendMessageToAll(String msg){
        WebSocketHandler.userChannels.writeAndFlush(new TextWebSocketFrame(msg));
    }

}