// Copyright (C) 2020 Meituan
// All rights reserved
package com.weiyun.controller;

import com.weiyun.dto.response.PeopleCountDTO;
import com.weiyun.response.CommonResult;
import com.weiyun.service.TbPeopleCountService;
import com.weiyun.dto.request.CommonQueryConditionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "数据接口")
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private TbPeopleCountService peopleCountService;

    @PostMapping("/query")
    @ApiOperation(value = "人流量数据普通查询",notes = "查询某年某月某日某小时段内人流量数据列表（每两分钟）")
    public CommonResult commonQuery(
            @RequestBody
            @ApiParam(name = "commonQueryConditionReqVO", value = "查询条件", required = true)
            @Valid CommonQueryConditionDTO commonQueryConditionReqVO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            //多个校验错误只返回第一个错误信息给前端
            return CommonResult.validateFailed(allErrors.get(0).getDefaultMessage());
        }
        List<PeopleCountDTO> list = peopleCountService.queryCommonData(commonQueryConditionReqVO);
        return CommonResult.success();
    }

}