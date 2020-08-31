package com.jlp.controller;

import com.jlp.pojo.Movie;
import com.jlp.pojo.Str;
import com.jlp.service.MovieService;
import com.jlp.service.StrService;
import com.jlp.utils.devidePage.PageInfoImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.jlp.utils.FileUtils.saveWorkFile;
import static com.jlp.utils.IPUtils.getRemoteIP;
import static com.jlp.utils.NameUtils.getName;
import static com.jlp.utils.TimeUtils.nowTime;

@Api("看片Controller")
@RestController
@Transactional
@RequestMapping("/movie")
public class MovieController {

    final static Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Resource
    MovieService movieService;
    @Resource
    StrService strService;

    @ApiOperation(value = "获取视频列表（视频目录）")
    @GetMapping
    List<Movie> getMovie(@RequestParam("now") Integer now, @RequestParam("size") Integer size) {
        PageInfoImpl<Movie> pageInfo = new PageInfoImpl<>(movieService.selectPidEqualFid(), now, size);
        pageInfo.sortContent(Comparator.comparing(Movie::getMpublishtime));
        return pageInfo.nowPage();
    }

    @ApiOperation(value = "获取指定FatherId的视频和弹幕内容")
    @GetMapping("/{fatherId}")
    List<Movie> getMovieByFId(@PathVariable Integer fatherId, @RequestParam("now") Integer now, @RequestParam("size") Integer size) {
        PageInfoImpl<Movie> pageInfo = new PageInfoImpl<>(movieService.selectByFid(fatherId), now, size);
        pageInfo.sortContent(Comparator.comparing(Movie::getMpublishtime));
        return pageInfo.nowPage();
    }

    @ApiOperation(value = "点赞某条弹幕或视频，参数为mid")
    @PutMapping("/{mid}")
    void addGreat(@PathVariable Integer mid) {
        movieService.addGreat(mid);
    }

    @ApiOperation(value = "插入", notes = "为母，传入 movie title 和desc 为子则之传入fatherId content timeInMovie")
    @PostMapping
    String postMovie(@RequestParam(value = "movie", required = false) MultipartFile mfile
            , @RequestParam(value = "title", required = false) String title
            , @RequestParam(value = "desc", required = false) String desc
            , @RequestParam(value = "content", required = false) String content
            , @RequestParam(value = "timeInMovie", required = false) Date timeInMovie
            , @RequestParam(value = "fatherId", required = false) Integer fatherId
            , HttpServletRequest request) throws IOException {
        Str str = new Str();
        str.setStype((byte) 2);
        strService.insert(str);
        Movie movie = new Movie();

        if (fatherId == null) {
            //上传前处理
            String uploadPath = "d://uploadFiles/" + File.separator + "/movie";
            File dir;
            dir = new File(uploadPath);
            logger.info("图片上传路径为" + uploadPath);
            if (!dir.exists()) {
                logger.warn("新建" + dir.getName());
                logger.warn("新建文件夹结果" + dir.mkdirs());
            }
            movie.setMtitle(title);
            movie.setMdesc(desc);
            movie.setMpublishtime(nowTime());
            movie.setMcontent(saveWorkFile(mfile, uploadPath));
            movie.setMfatherid(str.getSid());
        } else {
            movie.setMpublishtime(timeInMovie);
            movie.setMcontent(content);
            movie.setMfatherid(fatherId);
        }
        movie.setMid(str.getSid());
        movie.setMpublishname(getName());
        movie.setMgreat(0);
        movie.setMpubliship(getRemoteIP(request));
        if (movieService.insert(movie) != 0) return "postMovie success";
        else return "postMovie failed";
    }

    @ApiOperation(value = "删除视频", notes = "必须传入原始的sId串号")
    @DeleteMapping("/{sId}")
    String delMovie(@PathVariable Integer sId) {
        if (movieService.deleteByPrimaryKey(sId) != 0 && strService.deleteByPrimaryKey(sId) != 0)
            return "delMovie success";
        else return "delMovie failed";
    }


}
