package com.weiyun.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author Starix
 * @date 2021/3/6 17:31
 */
@Data
public class StatisticsDataDTO {

    @JsonIgnore
    private Integer areaCode;

    private String  date;

    private Integer maxCount;

    private Integer minCount;

    private Integer avgCount;

}
