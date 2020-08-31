package com.jlp.controller;

import com.jlp.mapper.ReportMapper;
import com.jlp.pojo.Report;
import com.jlp.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.jlp.utils.TimeUtils.nowTime;

@Api("举报Controller")
@RestController
@RequestMapping("/report")
public class ReportController {

    final static Logger logger = LoggerFactory.getLogger(ReportController.class);
    @Resource
    ReportService reportService;

    @ApiOperation(value = "获取所有的举报信息")
    @GetMapping
    List<Report> getReport() {
        return reportService.selectAll();
    }

    @ApiOperation(value = "新增举报信息,需传入rsid rreason（举报串号 举报理由）")
    @PostMapping
    Integer postReport(Report report) {
        report.setRtime(nowTime());
        return reportService.insert(report);
    }

    @ApiOperation(value = "删除指定的举报")
    @DeleteMapping("/{rid}")
    Integer delReport(@PathVariable Integer rid) {
        return reportService.deleteByPrimaryKey(rid);
    }


}
