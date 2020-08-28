package com.jlp.mapper;

import com.jlp.pojo.Braggart;
import com.jlp.pojo.Photo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PhotoMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Photo record);

    int insertSelective(Photo record);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    List<Photo> selectPidEqualFid();

    List<Photo> selectByFid(Integer pfatherId);

    void addGreat(Integer pid);

    @Select("select * from braggart where pId = #{pid}")
    List<Photo> selectByPid(Integer pid);
}