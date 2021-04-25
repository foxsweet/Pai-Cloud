package com.pai.common.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @创建人 dmm
 */
@Component
public class RedisService {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存对象，String Object
     *
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓string,Object.并且有过期时间
     *
     * @param key
     * @param value
     * @param timeOut
     * @param timeUnit
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value, final Long timeOut, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }


    /**
     * 更据Key获取val
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除指定val
     *
     * @param key
     * @return
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 设置有效时间
     * @param key
     * @param timeout
     * @return
     */
    public boolean expire(final String key,final long timeout){
        return expire(key,timeout,TimeUnit.SECONDS);
    }

    public boolean expire(final String key,final long timeout,final TimeUnit unit){
        return redisTemplate.expire(key,timeout,unit);
    }

}
