package com.jlp.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RedisBeanFactory {

    static RedisObjCache instance;

    RedisObjCache redisObjCache;

    @Autowired
    RedisBeanFactory(RedisObjCache redisObjCache) {
        instance = redisObjCache;
        System.out.println("init instance " + instance);
    }

    public static RedisObjCache getInstance() {
        return instance;
    }
}
