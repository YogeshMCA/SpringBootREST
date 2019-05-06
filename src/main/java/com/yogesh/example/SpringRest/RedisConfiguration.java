/*package com.yogesh.example.SpringRest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String HOST;
	@Value("${spring.redis.port}")
	private Integer PORT;
	@Value("${spring.redis.password}")
	private String PASS;
	
	
	@Bean(name="jedisConnFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(HOST);
		config.setPort(PORT);
		config.setPassword(PASS);
		JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().usePooling().build();
		JedisConnectionFactory factory = new JedisConnectionFactory(config,clientConfig);
		return factory;
	}
	
	@Bean(name="redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String,Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
*/