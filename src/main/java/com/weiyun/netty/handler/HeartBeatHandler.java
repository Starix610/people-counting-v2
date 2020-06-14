package com.weiyun.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 用于检测channel的心跳handler
 * 				 继承ChannelInboundHandlerAdapter，从而不需要实现channelRead0方法
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        // 判断evt是否是IdleStateEvent（用于触发用户事件，包括读空闲/写空闲/读写空闲，当空闲时间超过设定值后还未收到心跳信息则执行相关的操作）
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;		// 强制类型转换

            if (event.state() == IdleState.READER_IDLE){
                log.info("channel[{}]进入读空闲...", ctx.channel().id().asShortText());
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("channel[{}]进入写空闲...", ctx.channel().id().asShortText());
            } else if (event.state() == IdleState.ALL_IDLE) {
                Channel channel = ctx.channel();
                // 关闭超时未发送心跳包的无用的channel，以防资源浪费
                log.info("即将关闭超时未收到心跳包的channel: [{}]，current userChannels size: {}", channel.id().asShortText(), WebSocketHandler.userChannels.size());
                channel.close();
            }
        }

    }
}
