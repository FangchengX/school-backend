package com.adoujia.school.service;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fangcheng
 * @since 2020-10-25 23:01
 */
@Service
public class RedisService {
    @Autowired
    RedissonClient redissonClient;

    /**
     * save string value with expired time
     *
     * @param key      key
     * @param value    value
     * @param time     time
     * @param timeUnit timeUnit
     */
    public void save(String key, String value, long time, TimeUnit timeUnit) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(value, time, timeUnit);
    }

    /**
     * save string value
     *
     * @param key   key
     * @param value value
     */
    public void save(String key, String value) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    /**
     * get string value
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    /**
     * check key exist
     *
     * @param key key
     * @return exist or not
     */
    public boolean exist(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.isExists();
    }
}
