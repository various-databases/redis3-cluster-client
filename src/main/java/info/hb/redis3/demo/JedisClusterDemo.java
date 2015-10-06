package info.hb.redis3.demo;

import info.hb.redis3.cluster.client.Redis3ClusterClient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterDemo {

	public static void main(String args[]) {
		JedisCluster jc = Redis3ClusterClient.getCluster(9020, "192.168.32.199");

		String key = "11name";
		String result = jc.get(key);
		System.out.println(result);
		jc.close();
	}

	JedisCluster jc = null;

	@Before
	public void before() {
		jc = getCluster("192.168.32.199", 9020);
	}

	@Test
	public void test_incr() {

		String key = "page_view";
		jc.del(key);
		jc.incr(key);
		String result = jc.get(key);
		System.out.println(result);
		Assertions.assertThat(result).isEqualTo(1 + "");
	}

	@Test
	public void test_setAndGetStringVal() {
		String key = "foo";
		String value = "bar";
		jc.set(key, value);
		String result = jc.get(key);
		System.out.println(result);
		Assertions.assertThat(result).isEqualTo(value);
	}

	@Test
	public void test_setAndGetStringVal_and_set_expire() throws InterruptedException {
		String key = "hello";
		String value = "world";
		int seconds = 3;
		jc.setex(key, seconds, value);
		String result = jc.get(key);
		System.out.println(result);
		Assertions.assertThat(result).isEqualTo(value);
		Thread.sleep(seconds * 1000);
		result = jc.get(key);
		System.out.println(result);
		Assertions.assertThat(result).isNull();

	}

	@Test
	public void test_setAndGetHashVal() {

		String key = "website";
		String field = "google";
		String value = "google.com";
		jc.del(key);
		jc.hset(key, field, value);

		String result = jc.hget(key, field);
		System.out.println(result);
		Assertions.assertThat(result).isEqualTo(value);
	}

	@Test
	public void test_setAndGetListVal() {

		String key = "mylist";
		jc.del(key);
		String[] vals = { "a", "b", "c" };
		jc.rpush(key, vals);
		List<String> result = jc.lrange(key, 0, -1);
		System.out.println(result);
		Assertions.assertThat(result).containsExactly(vals);
	}

	@Test
	public void test_setAndGetSetVal() {

		String key = "language";
		jc.del(key);
		String[] members = { "java", "ruby", "python" };
		jc.sadd(key, members);
		Set<String> result = jc.smembers(key);
		System.out.println(result);
		Assertions.assertThat(result).containsOnly(members);
	}

	public static JedisCluster getCluster(String host, int port) {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort(host, port));
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);

		return jedisCluster;
	}

}
