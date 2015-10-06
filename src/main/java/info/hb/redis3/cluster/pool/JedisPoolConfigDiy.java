package info.hb.redis3.cluster.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class JedisPoolConfigDiy extends GenericObjectPoolConfig {

	public JedisPoolConfigDiy() {
		// defaults to make your life with connection pool easier :)
		setTestWhileIdle(true);
		setMinEvictableIdleTimeMillis(60000);
		setTimeBetweenEvictionRunsMillis(30000);
		setNumTestsPerEvictionRun(-1);
	}

}
