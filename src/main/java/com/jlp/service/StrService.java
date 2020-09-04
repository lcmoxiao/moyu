package com.jlp.service;

import com.jlp.mapper.StrMapper;
import com.jlp.pojo.Str;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@CacheConfig(cacheNames = "str")
@Service
public class StrService {

    @Resource
    StrMapper strMapper;

    public void insert(Str str) {
        strMapper.insert(str);
    }

    @CacheEvict(key = "#sid")
    public int deleteByPrimaryKey(Integer sid) {
        return strMapper.deleteByPrimaryKey(sid);
    }

    @Cacheable(key = "#sid")
    public Str selectByPrimaryKey(Integer sid) {
        return strMapper.selectByPrimaryKey(sid);
    }
}
