// Copyright (C) 2020 Meituan
// All rights reserved
package com.weiyun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weiyun.dto.request.HistoryQueryConditionDTO;
import com.weiyun.dto.request.StatisticsQueryConditionDTO;
import com.weiyun.entity.TbPeopleCount;
import com.weiyun.response.CommonResult;
import com.weiyun.service.TbPeopleCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  普通查询：
 *      某年某月某日某小时段内人流量数据列表
 *  统计查询：
 *      某天人流量统计数据（当日内每小时人数/告警次数：平均值、峰值、最低值，当日人数/告警次数：平均、峰值、最低值）
 *      某月人流量统计数据（当月内每天人数/告警次数：平均值、峰值、最低值，当月人数/告警次数：平均值、峰值、最低值）
 *
 *  @author shiwenjie
 *  @created 2020/6/16 10:54 上午
 **/
@Api(tags = "统计数据接口")
@RestController
@RequestMapping("/data")
@CrossOrigin
public class DataController {

    @Autowired
    private TbPeopleCountService peopleCountService;

    @PostMapping("/query")
    @ApiOperation(value = "人流量历史数据查询",notes = "查询人流量历史数据列表（每两分钟）")
    public CommonResult historyDataQuery(
            @RequestBody
            @ApiParam(name = "historyQueryConditionReqVO", value = "查询条件", required = true)
            @Valid HistoryQueryConditionDTO historyQueryConditionReqVO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            //多个校验错误只返回第一个错误信息给前端
            return CommonResult.validateFailed(allErrors.get(0).getDefaultMessage());
        }
        IPage<TbPeopleCount> iPage = peopleCountService.queryHistoryData(historyQueryConditionReqVO);
        return CommonResult.success(iPage);
    }


    @PostMapping("/statistics")
    @ApiOperation(value = "人流量统计数据查询",notes = "查询人流量统计数据（近7天，近30天，近90天每日统计数据）")
    public CommonResult statisticsQuery(
            @RequestBody
            @ApiParam(name = "statisticsQueryConditionReqVO", value = "查询条件", required = true)
            @Valid StatisticsQueryConditionDTO statisticsQueryConditionReqVO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            //多个校验错误只返回第一个错误信息给前端
            return CommonResult.validateFailed(allErrors.get(0).getDefaultMessage());
        }
        return CommonResult.success(peopleCountService.queryStatisticsData(statisticsQueryConditionReqVO));
    }

}