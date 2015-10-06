package info.hb.redis3.demo;

import info.hb.redis3.cluster.client.Redis3ClusterClient;

import org.junit.Before;

import redis.clients.jedis.JedisCluster;

public class LoopIODemo {
	JedisCluster jc = null;

	@Before
	public void before() {
		jc = Redis3ClusterClient.getCluster(9020, "192.168.32.199");

	}

	/**
	 * 在一个无限循环中不停的读写
	 *
	 * @throws InterruptedException
	 */
	@org.junit.Test
	public void setAndWriteStringValueRepeatedly() throws InterruptedException {
		String key = "test_oper_during_failover";
		jc.del(key);
		long failureTime = 0;
		long recoveryTime = 0;
		while (true) {
			try {
				String result = jc.get(key);
				if (failureTime != 0 && recoveryTime == 0) {
					recoveryTime = System.currentTimeMillis();
					System.out.println("Cluster is recovered! Downtime lasted " + (recoveryTime - failureTime) + " ms");
				}

				System.out.println(result);
				jc.set(key, System.currentTimeMillis() + "");

			} catch (Exception e) {
				if (failureTime == 0)
					failureTime = System.currentTimeMillis();
				e.printStackTrace();
			}
			Thread.sleep(1000);
		}
	}

}
