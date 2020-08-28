package com.jlp.controller;

import com.jlp.mapper.BraggartMapper;
import com.jlp.mapper.StrMapper;
import com.jlp.pojo.Braggart;
import com.jlp.pojo.Str;
import com.jlp.utils.devidePage.PageInfoImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

import static com.jlp.utils.IPUtils.getRemoteIP;
import static com.jlp.utils.NameUtils.getName;
import static com.jlp.utils.TimeUtils.nowTime;

@Api("嘴炮Controller")
@RestController
@Transactional
@RequestMapping("/braggart")
public class BraggartController {
    final static Logger logger = LoggerFactory.getLogger(BraggartController.class);

    @Resource
    BraggartMapper braggartMapper;

    @Resource
    StrMapper strMapper;

    @ApiOperation(value = "获取母嘴炮列表（嘴炮目录）")
    @GetMapping
    @Transactional
    public List<Braggart> getBraggart(@RequestParam("now") Integer now, @RequestParam("size") Integer size) {
        PageInfoImpl<Braggart> braggartPageInfo = new PageInfoImpl<>(braggartMapper.selectBidEqualFid(), now, size);
        braggartPageInfo.sortContent(Comparator.comparing(Braggart::getBpublishtime));
        return braggartPageInfo.nowPage();
    }

    @ApiOperation(value = "获取指定bfatherId的所有母子嘴炮（某详细嘴炮）")
    @GetMapping("/{bfatherId}")
    public List<Braggart> getBraggartByFId(@PathVariable Integer bfatherId, @RequestParam("now") Integer now, @RequestParam("size") Integer size) {
        PageInfoImpl<Braggart> braggartPageInfo = new PageInfoImpl<>(braggartMapper.selectByFid(bfatherId), now, size);
        braggartPageInfo.sortContent(Comparator.comparing(Braggart::getBpublishtime));
        return braggartPageInfo.nowPage();
    }

    @ApiOperation(value = "点赞某条嘴炮，参数为bid")
    @PutMapping("/{bid}")
    public void addGreat(@PathVariable Integer bid) {
        braggartMapper.addGreat(bid);
    }

    @ApiOperation(value = "插入", notes = "为母传入 content title desc 为子传入 content fatherId")
    @PostMapping
    String postBraggart(@RequestParam("content") String content
            , @RequestParam(value = "title", required = false) String title
            , @RequestParam(value = "desc", required = false) String desc
            , @RequestParam(value = "fatherId", required = false) Integer fatherId
            , HttpServletRequest request) {
        Str str = new Str();
        str.setStype((byte) 0);
        strMapper.insert(str);
        Braggart braggart = new Braggart();
        braggart.setBid(str.getSid());
        braggart.setBpublishtime(nowTime());
        braggart.setBpublishname(getName());
        braggart.setBgreat(0);
        braggart.setBpubliship(getRemoteIP(request));
        braggart.setBcontent(content);
        if (fatherId == null) {
            braggart.setBtitle(title);
            braggart.setBdesc(desc);
            fatherId = str.getSid();
        }
        braggart.setBfatherid(fatherId);
        if (braggartMapper.insert(braggart) != 0) return "postBraggart success";
        else return "postBraggart failed";
    }

    @ApiOperation(value = "删除嘴炮", notes = "必须传入原始的sId串号")
    @DeleteMapping("/{sId}")
    public String delBraggart(@PathVariable Integer sId) {
        if (braggartMapper.deleteByPrimaryKey(sId) != 0 && strMapper.deleteByPrimaryKey(sId) != 0)
            return "delBraggart success";
        else return "delBraggart failed";
    }
}
