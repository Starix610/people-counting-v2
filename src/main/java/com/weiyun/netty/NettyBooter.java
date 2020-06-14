// Copyright (C) 2020 Meituan
// All rights reserved
package com.weiyun.netty;

import com.weiyun.netty.init.WSServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * >>>>>> Netty服务端启动类 <<<<<<
 *
 * 当ioc容器加载处理完相应的bean之后，也给我们提供了一个机会（先有InitializingBean，后有ApplicationListener<ContextRefreshedEvent>），
 * 可以去做一些自己想做的事。其实这也就是spring ioc容器给提供的一个扩展的地方。我们可以这样使用这个扩展机制，让Netty在Spring容器初始化完成时启动
 * @author shiwenjie
 * @created 2020/6/11 3:51 下午
 **/
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private WSServer wsServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        /**
         * 在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,
         * 另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
         * 这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免这种问题，
         * 我们可以只在root application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理
         */
        if (event.getApplicationContext().getParent()==null){ //root application context 没有parent，他就是老大
            try {
                wsServer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}