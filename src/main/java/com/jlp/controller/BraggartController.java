package com.jlp.controller;

import com.jlp.mapper.BraggartMapper;
import com.jlp.mapper.StrMapper;
import com.jlp.pojo.Braggart;
import com.jlp.pojo.Str;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api("嘴炮Controller")
@RestController
@RequestMapping("/braggart")
public class BraggartController {

    @Resource
    BraggartMapper braggartMapper;
    @Resource
    StrMapper strMapper;

    @ApiOperation(value = "获取全部嘴炮列表")
    @GetMapping
    List<Braggart> getBraggart() {
        return braggartMapper.selectBidEqualFid();
    }

    @ApiOperation(value = "获取指定fatherId的所有母子嘴炮")
    @GetMapping("/{fatherId}")
    List<Braggart> getBraggartByFId(@PathVariable Integer fatherId) {
        return braggartMapper.selectByFatherId(fatherId);
    }

    @ApiOperation(value = "插入嘴炮")
    @PostMapping
    String postBraggart(Braggart braggart) {
        Str str = new Str();
        str.setStype((byte) 0);
        strMapper.insert(str);
        braggart.setBid(str.getSid());
        if (braggartMapper.insert(braggart) != 0)
            return "postBraggart success";
        else return "postBraggart failed";
    }

    @ApiOperation(value = "删除嘴炮", notes = "必须传入原始的sId串号")
    @DeleteMapping("/{sId}")
    String delBraggart(@PathVariable Integer sId) {
        if (braggartMapper.deleteByPrimaryKey(sId) != 0 && strMapper.deleteByPrimaryKey(sId) != 0)
            return "delBraggart success";
        else return "delBraggart failed";
    }

}
