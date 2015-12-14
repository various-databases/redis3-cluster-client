
## 使用示例

```java
		/* poolConfig */
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		
		/* connection pool */
		RedisConnectionPool pool = new RedisConnectionPool(config, "localhost", 6379);
		
		/* pool getConnection */
		Jedis jedis = pool.getConnection();
			
		...
		
		/* pool getConnection */
		pool.returnConnection(jedis);
```
