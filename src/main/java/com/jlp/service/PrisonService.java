package com.jlp.service;

import com.jlp.mapper.PrisonMapper;
import com.jlp.pojo.Prison;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "prison")
@Service
public class PrisonService {
    @Resource
    PrisonMapper prisonMapper;

    @Cacheable(key = "'listAll'")
    public List<Prison> selectAll() {
        return prisonMapper.selectAll();
    }

    @Cacheable(key = "#remoteIP")
    public Prison selectByIP(String remoteIP) {
        return prisonMapper.selectByIP(remoteIP);
    }

    @Caching(put = {@CachePut(key = "'list'+#prison.pip")}, evict = {@CacheEvict(key = "'listAll'")})
    public Integer insert(Prison prison) {
        return prisonMapper.insert(prison);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#pid")})
    public Integer deleteByPrimaryKey(Integer pid) {
        return prisonMapper.deleteByPrimaryKey(pid);
    }

}
