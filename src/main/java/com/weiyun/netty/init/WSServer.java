package com.weiyun.netty.init;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WSServer {

    @Value("${netty.port}")
    private int port;

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ServerBootstrap bootstrap;
    private ChannelFuture future;

    public WSServer(){
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class).childHandler(new WSServerInitialzer());
    }

    public void start(){
        try {
            this.future = bootstrap.bind(port).sync();
            log.info("Netty Server ["+ WSServer.class.getName()+"] started and listening on" + future.channel().localAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
