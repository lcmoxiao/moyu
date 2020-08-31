package com.jlp.controller;

import com.jlp.mapper.PrisonMapper;
import com.jlp.pojo.Prison;
import com.jlp.service.PrisonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.jlp.utils.TimeUtils.nowTime;

@Api("监狱Controller")
@RestController
@RequestMapping("/prison")
public class PrisonController {

    final static Logger logger = LoggerFactory.getLogger(PrisonController.class);
    @Resource
    PrisonService prisonService;

    @ApiOperation(value = "获取所有的封禁信息")
    @GetMapping
    List<Prison> getPrison() {
        return prisonService.selectAll();
    }

    @ApiOperation(value = "新增封禁信息,需传入pip psid preason（封禁IP 相关串号 封禁理由）")
    @PostMapping
    Integer postPrison(Prison prison) {
        logger.info("封禁了" + prison.getPip());
        prison.setPstarttime(nowTime());
        return prisonService.insert(prison);
    }

    @ApiOperation(value = "删除指定的封禁信息")
    @DeleteMapping("/{pid}")
    Integer delPrison(@PathVariable Integer pid) {
        return prisonService.deleteByPrimaryKey(pid);
    }


}
