package com.jlp.mapper;

import com.jlp.pojo.Photo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);
}