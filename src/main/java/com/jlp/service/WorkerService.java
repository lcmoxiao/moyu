package com.jlp.service;

import com.jlp.mapper.WorkerMapper;
import com.jlp.pojo.Worker;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "worker")
@Service
public class WorkerService {

    @Resource
    WorkerMapper workerMapper;

    @Cacheable(key = "'listAll'")
    public List<Worker> selectAll() {
        return workerMapper.selectAll();
    }
    
    @Caching(evict = {@CacheEvict(key = "'listAll'")})
    public Integer insert(Worker worker) {
        return workerMapper.insert(worker);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'")})
    public Integer deleteByPrimaryKey(Integer wid) {
        return workerMapper.deleteByPrimaryKey(wid);
    }
}
