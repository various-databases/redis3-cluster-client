package info.hb.redis3.pool.core;

import java.io.Serializable;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 默认池配置
 *
 * @author wanggang
 *
 */
public class PoolConfig extends GenericObjectPoolConfig implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -2414567557372345057L;

	/**
	 * <p>Title: PoolConfig</p>
	 * <p>Description: 默认构造方法</p>
	 */
	public PoolConfig() {
		// defaults to make your life with connection pool easier :)
		setTestWhileIdle(true);
		setMinEvictableIdleTimeMillis(60000);
		setTimeBetweenEvictionRunsMillis(30000);
		setNumTestsPerEvictionRun(-1);
	}

}
