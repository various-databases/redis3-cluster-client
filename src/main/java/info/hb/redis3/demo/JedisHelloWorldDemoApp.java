package info.hb.redis3.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisCluster;

public class JedisHelloWorldDemoApp {
	private static final Logger logger = LoggerFactory
			.getLogger(JedisHelloWorldDemoApp.class);

	public static void main(String[] args) {
		JedisCluster jc = RedisUtils.getCluster("192.168.32.199", 9020);

	}
}