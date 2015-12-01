package info.hb.redis3.cluster.client;

import info.hb.redis3.cluster.pool.JedisPoolConfig;
import info.hb.redis3.cluster.utils.RedisConfig;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Redis3集群的客户端
 *
 * @author wanggang
 *
 */
public class Redis3ClusterClient {

	private static Logger logger = LoggerFactory.getLogger(Redis3ClusterClient.class);

	private JedisCluster jedisCluster;

	public Redis3ClusterClient() {
		init();
	}

	private void init() {
		Properties props = RedisConfig.getProps("redis3-cluster.properties");
		String ips = props.getProperty("redis.ips");
		int port = Integer.parseInt(props.getProperty("redis.port"));
		Set<HostAndPort> nodes = new HashSet<>();
		for (String ip : ips.split(",")) {
			nodes.add(new HostAndPort(ip, port));
		}
		jedisCluster = new JedisCluster(nodes, Integer.parseInt(props.getProperty("redis.timeout")),
				new JedisPoolConfig());
		logger.info("Initing redis-cluster with Host:{}, Port:{}", ips, port);
	}

	/***********************集合操作命令*************************/

	public void sadd(String key, String... member) {
		jedisCluster.sadd(key, member);
	}

	public void scard(String key) {
		jedisCluster.scard(key);
	}

	public String spop(String key) {
		return jedisCluster.spop(key);
	}

	// 有错误，搞不清count是什么意思
	public Set<String> spop(String key, long count) {
		return jedisCluster.spop(key, count);
	}

	public Set<String> smember(String key) {
		return jedisCluster.smembers(key);
	}

	/***********************集合操作命令**************************/

	public void reset() {
		init();
	}

	public void reset(int port, int timeout, String... ips) {
		Set<HostAndPort> nodes = new HashSet<>();
		for (String ip : ips) {
			nodes.add(new HostAndPort(ip, port));
		}
		jedisCluster = new JedisCluster(nodes, timeout);
	}

	public static JedisCluster getCluster(int port, String... ips) {
		return getCluster(port, -1, ips);
	}

	public static JedisCluster getCluster(int port, int timeout, String... ips) {
		Set<HostAndPort> nodes = new HashSet<>();
		for (String ip : ips) {
			nodes.add(new HostAndPort(ip, port));
		}
		if (timeout == -1) {
			return new JedisCluster(nodes);
		} else {
			return new JedisCluster(nodes, timeout);
		}
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		jedisCluster.close();
	}

}
