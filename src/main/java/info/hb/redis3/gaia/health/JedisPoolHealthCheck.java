package info.hb.redis3.gaia.health;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.codahale.metrics.health.HealthCheck;

public class JedisPoolHealthCheck extends HealthCheck {

	private JedisPool jedisPool;

	public JedisPoolHealthCheck(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	protected Result check() throws Exception {
		try (Jedis jedis = jedisPool.getResource();) {
			String reply = jedis.ping();
			if (!reply.equalsIgnoreCase("PONG")) {
				return Result.unhealthy("ping response is not pong");
			}
		} catch (Exception e) {
			return Result.unhealthy(e.getMessage());
		}

		return Result.healthy();
	}

}