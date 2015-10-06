package info.hb.redis3.demo;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisUtils {

	public static JedisCluster getCluster(String host, int port) {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort(host, port));
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);

		return jedisCluster;
	}

	//	public static void main(String args[]) {
	//		ResourceBundle bundle = ResourceBundle.getBundle("redis");
	//		System.out.println(bundle.getString("redis.ip"));
	//	}
}
