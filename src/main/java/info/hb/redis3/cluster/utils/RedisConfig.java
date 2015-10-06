package info.hb.redis3.cluster.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisConfig {

	private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	public static Properties getProps(String confFileName) {
		Properties result = new Properties();
		logger.info("Load resource: " + confFileName);
		try (InputStream in = RedisConfig.class.getClassLoader().getResourceAsStream(confFileName);) {
			result.load(in);
			return result;
		} catch (final Exception e) {
			logger.error("{} is not found.", confFileName);
			throw new RuntimeException(e);
		}
	}

}
