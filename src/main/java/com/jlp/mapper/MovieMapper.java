package com.jlp.mapper;

import com.jlp.pojo.Movie;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieMapper {
    @Delete("delete from movie where mFatherId = #{mid} OR  mId = #{mid}")
    int deleteByPrimaryKey(Integer mid);

    int insert(Movie record);

    int insertSelective(Movie record);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);

    List<Movie> selectPidEqualFid();

    List<Movie> selectByFid(Integer fatherId);

    void addGreat(Integer mid);

    @Select("select * from movie where mId = #{mid}")
    Movie selectByMid(Integer mid);
}