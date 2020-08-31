package com.jlp.service;

import com.jlp.mapper.ReportMapper;
import com.jlp.pojo.Report;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "report")
@Service
public class ReportService {

    @Resource
    ReportMapper reportMapper;

    @Cacheable(key = "'listAll'")
    public List<Report> selectAll() {
        return reportMapper.selectAll();
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'")})
    public Integer insert(Report report) {
        return reportMapper.insert(report);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'")})
    public Integer deleteByPrimaryKey(Integer rid) {
        return reportMapper.deleteByPrimaryKey(rid);
    }
}
