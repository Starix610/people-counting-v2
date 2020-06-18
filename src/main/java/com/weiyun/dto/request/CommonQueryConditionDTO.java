// Copyright (C) 2020 Meituan
// All rights reserved
package com.weiyun.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

/**
 * @author shiwenjie
 * @created 2020/6/16 11:06 上午
 **/
@Data
public class CommonQueryConditionDTO {

    @ApiModelProperty("年份")
    @NotNull(message = "年份条件不能为空")
    private Integer year;

    @ApiModelProperty("月份")
    @NotNull(message = "月份条件不能为空")
    private Integer month;

    @ApiModelProperty("日")
    @NotNull(message = "日期条件不能为空")
    private Integer day;

    @ApiModelProperty("起始小时")
    private Integer startHour = 0;

    @ApiModelProperty("结束小时")
    private Integer endHour = 24;

    @ApiModelProperty("当前第几页。缺省值：默认第1页。")
    private Integer current = 1;

    @ApiModelProperty("每页条数。缺省值：默认20条。")
    private Integer size = 10;

    /**
     * 组装起始查询时间
     * @return
     */
    public Date builderStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.getYear(), this.getMonth()-1, this.getDay(), this.getStartHour(), 0, 0);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 组装结束查询时间
     * @return
     */
    public Date builderEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.getYear(), this.getMonth()-1, this.getDay(), this.getEndHour(), 0, 0);
        Date date = calendar.getTime();
        return date;
    }

}