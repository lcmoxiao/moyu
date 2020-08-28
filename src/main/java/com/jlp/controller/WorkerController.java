package com.jlp.controller;

import com.jlp.mapper.WorkerMapper;
import com.jlp.pojo.Worker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.jlp.utils.TimeUtils.nowTime;

@Api("值班员管理Controller")
@RestController
@RequestMapping("/worker")
public class WorkerController {

    final static Logger logger = LoggerFactory.getLogger(WorkerController.class);
    @Resource
    WorkerMapper workerMapper;

    @ApiOperation(value = "获取所有的值班人员信息")
    @GetMapping
    List<Worker> getWorker() {
        return workerMapper.selectAll();
    }

    @ApiOperation(value = "新增值班人员信息,需传入 ①姓名 ②密码 ③昵称")
    @PostMapping
    Integer postWorker(Worker worker) {
        worker.setOrdertime(nowTime());
        return workerMapper.insert(worker);
    }

    @ApiOperation(value = "删除指定的值班人员")
    @DeleteMapping("/{wid}")
    Integer delReport(@PathVariable Integer wid) {
        return workerMapper.deleteByPrimaryKey(wid);
    }


}
