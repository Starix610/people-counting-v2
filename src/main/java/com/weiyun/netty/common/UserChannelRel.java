package com.weiyun.netty.common;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 维护在线用户ID和channel的关联关系
 */
public class UserChannelRel {

	private static ConcurrentHashMap<String, Channel> manager = new ConcurrentHashMap<>();

	public static void put(String senderId, Channel channel) {
		manager.put(senderId, channel);
	}
	
	public static Channel get(String senderId) {
		return manager.get(senderId);
	}
	
	public static void output() {
		for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
			System.out.println("UserId: " + entry.getKey() + ", ChannelId: " + entry.getValue().id().asLongText());
		}
	}
}
