package info.hb.redis3.demo;

/*
 * 测试端口 9020 单线程写一千万条数据  没有丢失数据
 */
import redis.clients.jedis.JedisCluster;

public class Test1 {

	public static void main(String[] args) {
		try {
			JedisCluster jc = RedisUtils.getCluster("192.168.32.199", 9030);
			System.out.println(jc.get("1"));

			// for (int i = 100; i < Math.pow(10, 3); i++) {
			// jc.append("key" + i, "value" + i);
			// System.out.println(i);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
