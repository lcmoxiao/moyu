package com.jlp.mapper;

import com.jlp.pojo.Braggart;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BraggartMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Braggart record);

    int insertSelective(Braggart record);

    int updateByPrimaryKeySelective(Braggart record);

    int updateByPrimaryKey(Braggart record);

    List<Braggart> selectBidEqualFid();

    List<Braggart> selectByFid(Integer bfatherId);

    void addGreat(Integer bid);

    @Select("select * from braggart where bId = #{bid}")
    List<Braggart> selectByBid(Integer sid);
}