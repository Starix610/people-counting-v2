package com.weiyun.dto.response;

import lombok.Data;

/**
 * @author Starix
 * @date 2021/3/7 9:10
 */
@Data
public class DetectedResultDTO {

    private String originImage;

    private String detectedImage;

    private Integer detectedCount;

    private Integer time;

}
