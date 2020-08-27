package com.jlp.mapper;

import com.jlp.pojo.Report;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);
}