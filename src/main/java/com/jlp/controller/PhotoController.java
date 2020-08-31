package com.jlp.controller;

import com.jlp.pojo.Photo;
import com.jlp.pojo.Str;
import com.jlp.service.PhotoService;
import com.jlp.service.StrService;
import com.jlp.utils.devidePage.PageInfoImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static com.jlp.utils.FileUtils.saveWorkFile;
import static com.jlp.utils.IPUtils.getRemoteIP;
import static com.jlp.utils.NameUtils.getName;
import static com.jlp.utils.TimeUtils.nowTime;

@Api("斗图Controller")
@RestController
@RequestMapping("/photo")
public class PhotoController {

    final static Logger logger = LoggerFactory.getLogger(PhotoController.class);
    @Resource
    PhotoService photoService;
    @Resource
    StrService strService;

    @ApiOperation(value = "获取母斗图列表（斗图目录）")
    @GetMapping
    List<Photo> getPhoto(@RequestParam("now") Integer now, @RequestParam("size") Integer size) {
        PageInfoImpl<Photo> pageInfo = new PageInfoImpl<>(photoService.selectPidEqualFid(), now, size);
        pageInfo.sortContent(Comparator.comparing(Photo::getPpublishtime));
        return pageInfo.nowPage();
    }

    @ApiOperation(value = "获取指定pfatherId的所有母子斗图（某斗图的详细内容）")
    @GetMapping("/{pfatherId}")
    List<Photo> getPhotoByFId(@PathVariable Integer pfatherId, @RequestParam("now") Integer now, @RequestParam("size") Integer size) {
        PageInfoImpl<Photo> pageInfo = new PageInfoImpl<>(photoService.selectByFid(pfatherId), now, size);
        pageInfo.sortContent(Comparator.comparing(Photo::getPpublishtime));
        return pageInfo.nowPage();
    }

    @ApiOperation(value = "点赞某张图片，参数为pid")
    @PutMapping("/{pid}")
    void addGreat(@PathVariable Integer pid) {
        photoService.addGreat(pid);
    }

    @ApiOperation(value = "插入", notes = "为母传入 img title desc 为子传入 img fatherId")
    @PostMapping
    String postPhoto(@RequestParam("img") MultipartFile img
            , @RequestParam(value = "title", required = false) String title
            , @RequestParam(value = "desc", required = false) String desc
            , @RequestParam(value = "fatherId", required = false) Integer fatherId
            , HttpServletRequest request) throws IOException {
        //上传前处理
        String uploadPath = "d://uploadFiles/" + File.separator + "/photo";
        File dir;
        dir = new File(uploadPath);
        logger.info("图片上传路径为" + uploadPath);
        if (!dir.exists()) {
            logger.warn("新建" + dir.getName());
            logger.warn("新建文件夹结果" + dir.mkdirs());
        }

        Str str = new Str();
        str.setStype((byte) 1);
        strService.insert(str);
        Photo photo = new Photo();
        photo.setPid(str.getSid());
        photo.setPpublishtime(nowTime());
        photo.setPpublishname(getName());
        photo.setPgreat(0);
        photo.setPpubliship(getRemoteIP(request));
        photo.setPcontent(saveWorkFile(img, uploadPath));
        if (fatherId == null) {
            logger.info("fatherId为空");
            photo.setPtitle(title);
            photo.setPdesc(desc);
            fatherId = str.getSid();
        }
        photo.setPfatherid(fatherId);
        if (photoService.insert(photo) != 0) return "postPhoto success";
        else return "postPhoto failed";
    }

    @ApiOperation(value = "删除斗图", notes = "必须传入原始的sId串号")
    @DeleteMapping("/{sId}")
    String delPhoto(@PathVariable Integer sId) {
        if (photoService.deleteByPrimaryKey(sId) != 0 && strService.deleteByPrimaryKey(sId) != 0)
            return "delPhoto success";
        else return "delPhoto failed";
    }

}
