// Copyright (C) 2020 Meituan
// All rights reserved
package com.weiyun.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author shiwenjie
 * @created 2020/6/16 11:06 上午
 **/
@Data
public class CommonQueryConditionReqVO {

    @ApiModelProperty("年份")
    @NotBlank(message = "年份条件不能为空")
    private String year;

    @ApiModelProperty("月份")
    @NotBlank(message = "月份条件不能为空")
    private String month;

    @ApiModelProperty("日")
    @NotBlank(message = "日期条件不能为空")
    private String day;

    @ApiModelProperty("起始小时")
    private String startHour;

    @ApiModelProperty("结束小时")
    private String endHour;

}