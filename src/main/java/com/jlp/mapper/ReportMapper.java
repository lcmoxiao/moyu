package com.jlp.mapper;

import com.jlp.pojo.Report;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);

    @Select("select * from report")
    List<Report> selectAll();
}