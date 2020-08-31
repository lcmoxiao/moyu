package com.jlp.service;

import com.jlp.mapper.BraggartMapper;
import com.jlp.pojo.Braggart;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "braggart")
@Service
public class BraggartService {

    @Resource
    BraggartMapper braggartMapper;

    @Cacheable(key = "'listAll'")
    public List<Braggart> selectBidEqualFid() {
        return braggartMapper.selectBidEqualFid();
    }

    @Cacheable(key = "'list'+#bfatherId")
    public List<Braggart> selectByFid(Integer bfatherId) {
        return braggartMapper.selectByFid(bfatherId);
    }

    @CacheEvict(key = "'list'+#bid")
    public void addGreat(Integer bid) {
        braggartMapper.addGreat(bid);
    }

    @Caching(put = {@CachePut(key = "'list'+#braggart.bfatherid")}, evict = {@CacheEvict(key = "'listAll'")})
    public int insert(Braggart braggart) {
        return braggartMapper.insert(braggart);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#sId")})
    public int deleteByPrimaryKey(Integer sId) {
        return braggartMapper.deleteByPrimaryKey(sId);
    }
}
