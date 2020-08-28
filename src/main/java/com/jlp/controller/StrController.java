package com.jlp.controller;

import com.jlp.mapper.BraggartMapper;
import com.jlp.mapper.MovieMapper;
import com.jlp.mapper.PhotoMapper;
import com.jlp.mapper.StrMapper;
import com.jlp.pojo.Braggart;
import com.jlp.pojo.Movie;
import com.jlp.pojo.Photo;
import com.jlp.pojo.Str;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/str")
@RestController
public class StrController {

    final static Logger logger = LoggerFactory.getLogger(StrController.class);
    @Resource
    StrMapper strMapper;
    @Resource
    BraggartMapper braggartMapper;
    @Resource
    PhotoMapper photoMapper;
    @Resource
    MovieMapper movieMapper;

    @GetMapping("/{sid}")
    StrInfo jumpToStr(@PathVariable Integer sid) {
        Str str = strMapper.selectByPrimaryKey(sid);
        StrInfo strInfo = new StrInfo();
        switch (str.getStype()) {
            case 0 -> {
                logger.info("查的是嘴炮");
                strInfo.bs.addAll(braggartMapper.selectByBid(sid));
            }
            case 1 -> {
                logger.info("查的是斗图");
                strInfo.ps.addAll(photoMapper.selectByPid(sid));
            }
            case 2 -> {
                logger.info("查的是看片");
                strInfo.ms.addAll(movieMapper.selectByMid(sid));
            }
        }
        return strInfo;
    }


    @Getter
    @Setter
    private static class StrInfo {
        List<Braggart> bs = new ArrayList<>();
        List<Photo> ps = new ArrayList<>();
        List<Movie> ms = new ArrayList<>();
        byte infoType;
    }


}
