package com.jlp.service;

import com.jlp.mapper.PhotoMapper;
import com.jlp.pojo.Photo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "photo")
@Service
public class PhotoService {

    @Resource
    PhotoMapper photoMapper;

    @Cacheable(key = "'listAll'")
    public List<Photo> selectPidEqualFid() {
        return photoMapper.selectPidEqualFid();
    }

    @Cacheable(key = "'list'+#fatherId")
    public List<Photo> selectByFid(Integer fatherId) {
        return photoMapper.selectByFid(fatherId);
    }

    @CacheEvict(key = "'list'+#pid")
    public void addGreat(Integer pid) {
        photoMapper.addGreat(pid);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#photo.pfatherid")})
    public int insert(Photo photo) {
        return photoMapper.insert(photo);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#sId")})
    public int deleteByPrimaryKey(Integer sId) {
        return photoMapper.deleteByPrimaryKey(sId);
    }
}
