package com.jlp.mapper;

import com.jlp.pojo.Worker;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorkerMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Worker record);

    int insertSelective(Worker record);

    Worker selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);

    @Select("select * from worker")
    List<Worker> selectAll();

    @Select("select * from worker where username = '${username}'")
    Worker selectByUsername(String s);
}