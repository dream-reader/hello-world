package com.fulihui.cloud.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by fulihui on 2018/1/3.
 */
@Configuration
@EnableConfigurationProperties({RedisProperties.class})
public class RedisConfiguration
{
    @Bean
    public JedisPoolConfig jedisPoolConfig(RedisProperties redisProperties)
    {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(RedisProperties redisProperties, JedisPoolConfig jedisPoolConfig)
    {
        return new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeOut(), redisProperties.getPassWord());
    }
}
