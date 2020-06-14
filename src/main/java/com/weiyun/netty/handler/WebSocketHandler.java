package com.weiyun.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    // ChannelGroup用于记录和管理所有客户端的channel，保存到一个组里面进行管理
    public static ChannelGroup userChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * TextWebSocketFrame : 在Websokcet中专门用于处理文本数据的对象（帧）
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg){

        // 获取客户端传输过来的消息(Json格式)
        String content = msg.text();

        Channel clientChannel = ctx.channel();

        //发送消息给所有在线客户端
        userChannels.writeAndFlush(new TextWebSocketFrame(content));
    }



    /**
     *  当客户端连接服务端后，获取客户端当前客户端对应的handler,添加到group中
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        log.info("新客户端连接: channel[{}]", ctx.channel().id().asShortText());
        userChannels.add(ctx.channel());
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        //当触发handlerRemoved时会自动移除对应客户端的channel,可以不写下面这行代码
        log.info("客户端断开连接: channel[{}]", ctx.channel().id().asShortText());
        userChannels.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        userChannels.remove(ctx.channel());
    }
}
