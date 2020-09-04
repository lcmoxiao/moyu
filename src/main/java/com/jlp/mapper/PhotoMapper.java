package com.jlp.mapper;

import com.jlp.pojo.Photo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PhotoMapper {
    @Delete("delete from photo where pFatherId = #{pid} OR  pId = #{pid}")
    int deleteByPrimaryKey(Integer pid);

    int insert(Photo record);

    int insertSelective(Photo record);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    List<Photo> selectPidEqualFid();

    List<Photo> selectByFid(Integer pfatherId);

    void addGreat(Integer pid);

    @Select("select * from photo where pId = #{key}")
    Photo selectByKey(Integer key);

}