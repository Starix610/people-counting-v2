package com.weiyun.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Starix
 * @date 2021/3/6 17:25
 */
@Data
public class StatisticsQueryConditionDTO {

    @ApiModelProperty("区域ID")
    @NotNull(message = "区域ID不能为空")
    private Integer areaCode;


    @ApiModelProperty("时间间隔（过去7天，30天，90天）")
    @NotNull(message = "时间间隔不能为空")
    private Integer interval;

}
