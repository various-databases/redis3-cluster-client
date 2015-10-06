package info.hb.redis3.gaia;

import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GaiaConfiguration extends Configuration {

	@Valid
	@NotNull
	private JedisPoolFactory jedisPoolFactory = new JedisPoolFactory();

	@JsonProperty("redis")
	public JedisPoolFactory getJedisPoolFactory() {
		return jedisPoolFactory;
	}

	@JsonProperty("redis")
	public void setJedisPoolFactory(JedisPoolFactory jedisPoolFactory) {
		this.jedisPoolFactory = jedisPoolFactory;
	}

}
