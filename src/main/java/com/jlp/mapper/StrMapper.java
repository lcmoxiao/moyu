package com.jlp.mapper;

import com.jlp.pojo.Str;

public interface StrMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Str record);

    int insertSelective(Str record);

    Str selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Str record);

    int updateByPrimaryKey(Str record);
}