package info.hb.redis3.cluster.client;

import org.junit.Test;

public class Redis3ClusterClientTest {

	@Test
	public void testSet() {
		Redis3ClusterClient client = new Redis3ClusterClient();
		//		client.sadd("test", new String[] { "aaa", "bbb", "ccc" });
		System.out.println(client.smember("test"));
		System.out.println(client.spop("test"));
		System.out.println(client.smember("test"));
	}

}
