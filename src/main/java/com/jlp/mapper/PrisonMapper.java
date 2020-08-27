package com.jlp.mapper;

import com.jlp.pojo.Prison;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrisonMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Prison record);

    int insertSelective(Prison record);

    Prison selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Prison record);

    int updateByPrimaryKey(Prison record);
}