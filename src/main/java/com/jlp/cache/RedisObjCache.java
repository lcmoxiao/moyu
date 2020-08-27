package com.jlp.cache;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Repository(value = "redisObjCache")
@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)
public class RedisObjCache {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, 6000, TimeUnit.SECONDS);
    }

    public void delValue(String key) {
        redisTemplate.delete(key);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
