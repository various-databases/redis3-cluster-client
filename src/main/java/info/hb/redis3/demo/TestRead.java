package info.hb.redis3.demo;

import java.util.Map;

import redis.clients.jedis.JedisCluster;

public class TestRead {

	// 读写同时
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		JedisCluster jc = RedisUtils.getCluster("192.168.32.199", 9020);
//		jc.flushAll();
//		Map<String, JedisPool> nodes = jc.getClusterNodes();
//        Iterator<Map.Entry<String, JedisPool>> iterNodes = nodes.entrySet().iterator();
//        int i = 0;
//        while (iterNodes.hasNext()) {
//        	
//        }
		for (int i = 0; i < Math.pow(10, 8); i++) {
			System.out.println(jc.get("key" + i));
		}
	}

	public static void set(Map<String, String> entries, JedisCluster jc) {
		for (Map.Entry<String, String> entry : entries.entrySet()) {
			jc.set(entry.getKey(), entry.getValue());
		}
	}
}  
