package com.jlp.mapper;

import com.jlp.pojo.Movie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);
}