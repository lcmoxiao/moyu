package com.jlp.service;

import com.jlp.mapper.BraggartMapper;
import com.jlp.pojo.Braggart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "braggart")
@Service
public class BraggartService {

    final static Logger logger = LoggerFactory.getLogger(BraggartService.class);
    @Resource
    BraggartMapper braggartMapper;

    @Cacheable(key = "'listAll'")
    public List<Braggart> selectBidEqualFid() {
        return braggartMapper.selectBidEqualFid();
    }

    @Cacheable(key = "'list'+#bfatherId")
    public List<Braggart> selectByFid(Integer bfatherId) {
        logger.info("正在查询bfatherID：" + bfatherId);
        return braggartMapper.selectByFid(bfatherId);
    }

    @CacheEvict(key = "'list'+#bid")
    public void addGreat(Integer bid) {
        braggartMapper.addGreat(bid);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#braggart.bfatherid")})
    public int insert(Braggart braggart) {
        return braggartMapper.insert(braggart);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#sId")})
    public int deleteByPrimaryKey(Integer sId) {
        return braggartMapper.deleteByPrimaryKey(sId);
    }
}
