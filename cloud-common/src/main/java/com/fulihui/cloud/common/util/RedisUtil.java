package com.fulihui.cloud.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Objects;

/**
 * Created by fulihui on 2018/1/3.
 */
public final class RedisUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    private RedisUtil()
    {

    }

    public static long setnx(JedisPool jedisPool, String key, String value)
    {
        long result = 0;

        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.setnx(key, value);
        }
        catch (Exception e)
        {
            LOGGER.error("setnx，key:{},value:{},失败：{}", key, value, e);
        }
        return result;
    }

    public static String set(JedisPool jedisPool, String key, String value)
    {
        String result = null;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.set(key, value);
        }
        catch (Exception e)
        {
            LOGGER.error("get，key:{},失败：{}", key, e);
        }
        return result;
    }

    public static String get(JedisPool jedisPool, String key)
    {
        String result = null;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.get(key);
        }
        catch (Exception e)
        {
            LOGGER.error("get，key:{},失败：{}", key, e);
        }
        return result;
    }

    public static long del(JedisPool jedisPool, String key)
    {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.del(key);
        }
        catch (Exception e)
        {
            LOGGER.error("del，key:{},失败：{}", key, e);
        }
        return result;
    }

    public static String hget(JedisPool jedisPool, String redisKey, String mapKey)
    {
        String result = null;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.hget(redisKey, mapKey);
        }
        catch (Exception e)
        {
            LOGGER.error("hget，key:{},value:{},失败：{}", redisKey, mapKey, e);
        }
        return result;
    }

    public static long hset(JedisPool jedisPool, String redisKey, String mapKey, String value)
    {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.hset(redisKey, mapKey, value);
        }
        catch (Exception e)
        {
            LOGGER.error("hset，redisKey:{},mapKey:{}, value:{},失败：{}", redisKey, mapKey, value, e);
        }
        return result;
    }

    public static long decr(JedisPool jedisPool, String key, long value)
    {
        long result = -1;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.decrBy(key, value);
        }
        catch (Exception e)
        {
            LOGGER.error("decr，key:{},value:{},失败：{}", key, value, e);
        }
        return result;
    }

    public static long incr(JedisPool jedisPool, String key)
    {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.incr(key);
        }
        catch (Exception e)
        {
            LOGGER.error("incr，key:{},失败：{}", key, e);
        }
        return result;
    }

    public static long incrBy(JedisPool jedisPool, String key, long value)
    {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.incrBy(key, value);
        }
        catch (Exception e)
        {
            LOGGER.error("incrBy，key:{},value:{},失败：{}", key, value, e);
        }
        return result;
    }

    public static long decr(JedisPool jedisPool, String key)
    {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.decr(key);
        }
        catch (Exception e)
        {
            LOGGER.error("decr，key:{},失败：{}", key, e);
        }
        return result;
    }

    public static long decrBy(JedisPool jedisPool, String key, long value)
    {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.decrBy(key, value);
        }
        catch (Exception e)
        {
            LOGGER.error("decrBy，key:{},value:{},失败：{}", key, value, e);
        }
        return result;
    }

    public static long expire(JedisPool jedisPool, String key, int value)
    {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.expire(key, value);
        }
        catch (Exception e)
        {
            LOGGER.error("expire，key:{},value:{},失败：{}", key, value, e);
        }
        return result;
    }

    public static long append(JedisPool jedisPool, String key, String value)
    {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.append(key, value);
        }
        catch (Exception e)
        {
            LOGGER.error("append，key:{},value:{},失败：{}", key, value, e);
        }
        return result;
    }

    public static String lpop(JedisPool jedisPool, String key)
    {
        String result = null;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.lpop(key);
        }
        catch (Exception e)
        {
            LOGGER.error("lpop，key:{},失败：{}", key, e);
        }
        return result;
    }

    public static String rpop(JedisPool jedisPool, String key)
    {
        String result = null;
        try (Jedis jedis = jedisPool.getResource())
        {
            result = jedis.rpop(key);
        }
        catch (Exception e)
        {
            LOGGER.error("rpop，key:{},失败：{}", key, e);
        }
        return result;
    }

    public static void rpush(JedisPool jedisPool, String key, String value)
    {
        try (Jedis jedis = jedisPool.getResource())
        {
            if (Objects.nonNull(value))
            {
                jedis.rpush(key, value);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("rpush，key:{},失败：{}", key, e);
        }
    }

    public static void rpush(JedisPool jedisPool, String key, List<String> values)
    {
        try (Jedis jedis = jedisPool.getResource())
        {
            if (Objects.nonNull(values))
            {
                values.parallelStream().forEach(t -> {
                    if (StringUtil.isNotEmpty(t))
                    {
                        jedis.rpush(key, t);
                    }
                });
            }
        }
        catch (Exception e)
        {
            LOGGER.error("rpush，key:{},失败：{}", key, e);
        }
    }
}
