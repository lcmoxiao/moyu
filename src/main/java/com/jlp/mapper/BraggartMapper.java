package com.jlp.mapper;

import com.jlp.pojo.Braggart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BraggartMapper {
    @Delete("delete from braggart where bFatherId = #{bid} OR  bId = #{bid}")
    int deleteByPrimaryKey(Integer bid);

    int insert(Braggart bid);

    int insertSelective(Braggart record);

    int updateByPrimaryKeySelective(Braggart record);

    int updateByPrimaryKey(Braggart record);

    List<Braggart> selectBidEqualFid();

    List<Braggart> selectByFid(Integer bfatherId);

    void addGreat(Integer bid);

    @Select("select * from braggart where bId = #{key}")
    Braggart selectByKey(Integer key);
}