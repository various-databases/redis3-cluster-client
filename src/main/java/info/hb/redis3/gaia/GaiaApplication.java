package info.hb.redis3.gaia;

import info.hb.redis3.gaia.core.RedisDatastore;
import info.hb.redis3.gaia.health.JedisPoolHealthCheck;
import info.hb.redis3.gaia.resources.EventResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import redis.clients.jedis.JedisPool;

public class GaiaApplication extends Application<GaiaConfiguration> {

	public static void main(String[] args) throws Exception {
		new GaiaApplication().run(args);
	}

	@Override
	public String getName() {
		return "gaia";
	}

	@Override
	public void initialize(Bootstrap<GaiaConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(GaiaConfiguration configuration, Environment environment) {
		JedisPool pool = configuration.getJedisPoolFactory().build(environment);
		environment.healthChecks().register("jedis-pool", new JedisPoolHealthCheck(pool));

		RedisDatastore redisDatastore = new RedisDatastore(pool);
		final EventResource resource = new EventResource(redisDatastore);
		environment.jersey().register(resource);
	}

}
