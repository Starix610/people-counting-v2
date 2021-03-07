package com.weiyun.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author Starix
 * @date 2021/3/6 22:33
 */
@Data
public class IndexInitDataDTO {

    private Integer areaCode;

    private Integer maxCount;

    private Integer minCount;

    private List<StatisticsDataDTO> historyData;

}
