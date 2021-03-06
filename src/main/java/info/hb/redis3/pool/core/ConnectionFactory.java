package info.hb.redis3.pool.core;

import java.io.Serializable;

import org.apache.commons.pool2.PooledObjectFactory;

/**
 * 连接工厂接口
 *
 * @author wanggang
 *
 * @param <T>
 */
public interface ConnectionFactory<T> extends PooledObjectFactory<T>, Serializable {

	/**
	 * <p>Title: createConnection</p>
	 * <p>Description: 创建连接</p>
	 *
	 * @return 连接
	 * @throws Exception
	 */
	public abstract T createConnection() throws Exception;

}
