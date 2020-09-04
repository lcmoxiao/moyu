package com.jlp.controller;

import com.jlp.mapper.BraggartMapper;
import com.jlp.mapper.MovieMapper;
import com.jlp.mapper.PhotoMapper;
import com.jlp.pojo.Braggart;
import com.jlp.pojo.Movie;
import com.jlp.pojo.Photo;
import com.jlp.pojo.Str;
import com.jlp.service.StrService;
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
    StrService strService;
    @Resource
    BraggartMapper braggartMapper;
    @Resource
    PhotoMapper photoMapper;
    @Resource
    MovieMapper movieMapper;

    @GetMapping("/{sid}")
    StrInfo jumpToStr(@PathVariable Integer sid) {
        Str str = strService.selectByPrimaryKey(sid);
        StrInfo strInfo = new StrInfo();
        switch (str.getStype()) {
            case 0 -> {
                logger.info("查的是嘴炮");
                strInfo.bs.add(braggartMapper.selectByKey(sid));
            }
            case 1 -> {
                logger.info("查的是斗图");
                strInfo.ps.add(photoMapper.selectByKey(sid));
                strInfo.infoType = 1;
            }
            case 2 -> {
                logger.info("查的是看片");
                strInfo.infoType = 2;
                strInfo.ms.add(movieMapper.selectByMid(sid));
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
