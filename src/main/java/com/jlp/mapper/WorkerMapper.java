package com.jlp.mapper;

import com.jlp.pojo.Worker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkerMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Worker record);

    int insertSelective(Worker record);

    Worker selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);
}