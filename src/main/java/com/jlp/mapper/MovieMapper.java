package com.jlp.mapper;

import com.jlp.pojo.Movie;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Movie record);

    int insertSelective(Movie record);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);

    List<Movie> selectPidEqualFid();

    List<Movie> selectByFid(Integer fatherId);

    void addGreat(Integer mid);

    @Select("select * from braggart where mId = #{mid}")
    List<Movie> selectByMid(Integer mid);
}