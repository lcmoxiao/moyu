package com.jlp.mapper;

import com.jlp.pojo.Braggart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BraggartMapper {
    int deleteByPrimaryKey(@Param("bid") Integer bid);

    int insert(Braggart record);

    int insertSelective(Braggart record);

    List<Braggart> selectBidEqualFid();

    List<Braggart> selectByFatherId(Integer bFatherId);

    int updateByPrimaryKeySelective(Braggart record);

    int updateByPrimaryKey(Braggart record);
}