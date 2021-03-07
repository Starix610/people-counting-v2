package com.weiyun.dto.response;

import com.weiyun.entity.TbArea;
import lombok.Data;

/**
 * @author Starix
 * @date 2020-06-20 0:25
 */
@Data
public class AreaDTO {

    //区域代码
    private Integer areaCode;
    //区域名称
    private String areaName;
    //区域类型：0-室外密集型，1-室内密集型，2-室内人脸正面可见
    private Integer areaType;
    //人数阈值
    private Integer threshold;


    public AreaDTO convertToAreaDTO(TbArea area){
        this.areaCode = area.getAreaCode();
        this.areaName = area.getAreaName();
        this.areaType = area.getAreaType();
        this.threshold = area.getThreshold();
        return this;
    }

}
