package com.jlp.mapper;

import com.jlp.pojo.Prison;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PrisonMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Prison record);

    int insertSelective(Prison record);

    Prison selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Prison record);

    int updateByPrimaryKey(Prison record);

    @Select("select * from prison")
    List<Prison> selectAll();

    @Select("select * from prison where pIP = '${pip}'")
    Prison selectByIP(String pip);
}