package com.jlp.service;

import com.jlp.mapper.MovieMapper;
import com.jlp.pojo.Movie;
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

    @CacheEvict(key = "'list'+#id")
    public void addGreat(Integer id) {
        movieMapper.addGreat(id);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#movie.mfatherid")})
    public int insert(Movie movie) {
        return movieMapper.insert(movie);
    }

    @Caching(evict = {@CacheEvict(key = "'listAll'"), @CacheEvict(key = "'list'+#sId")})
    public int deleteByPrimaryKey(Integer sId) {
        return movieMapper.deleteByPrimaryKey(sId);
    }
}
