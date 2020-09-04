package com.jlp.service;

import com.jlp.mapper.MovieMapper;
import com.jlp.pojo.Movie;
import org.apache.ibatis.annotations.Delete;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "movie")
@Service
public class MovieService {

    @Resource
    MovieMapper movieMapper;

    @Cacheable(key = "'listAll'")
    public List<Movie> selectPidEqualFid() {
        return movieMapper.selectPidEqualFid();
    }

    @Cacheable(key = "'list'+#fatherId")
    public List<Movie> selectByFid(Integer fatherId) {
        return movieMapper.selectByFid(fatherId);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#fid"), @CacheEvict(key = "#id")})
    public void addGreat(Integer id, Integer fid) {
        movieMapper.addGreat(id);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#movie.mfatherid")})
    public int insert(Movie movie) {
        return movieMapper.insert(movie);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#fid"), @CacheEvict(key = "#sId")})
    public int deleteByPrimaryKey(Integer sId, Integer fid) {
        return movieMapper.deleteByPrimaryKey(sId);
    }

    public Movie selectByMid(Integer mid) {
       return movieMapper.selectByMid(mid);
    }

}
