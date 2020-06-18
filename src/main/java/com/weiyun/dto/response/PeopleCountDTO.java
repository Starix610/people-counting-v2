package com.weiyun.dto.response;

import com.weiyun.entity.TbPeopleCount;
import lombok.Data;

import java.util.Date;

/**
 * @author Starix
 * @date 2020-06-18 0:42
 */

@Data
public class PeopleCountDTO {

    private Integer id;
    //区域代码
    private Integer areaCode;
    //检测人数
    private Integer detectedCount;
    //是否超阈值
    private Boolean overflow;
    //检测后图片
    private String detectedImage;
    //原图片
    private String originImage;
    //记录时间
    private Date createTime;

    public PeopleCountDTO builderPeopleCountDTO(TbPeopleCount peopleCount){
        this.setId(peopleCount.getId());
        this.setAreaCode(peopleCount.getAreaCode());
        this.setDetectedCount(peopleCount.getDetectedCount());
        this.setOverflow(peopleCount.getOverflow());
        this.setDetectedImage(peopleCount.getDetectedImage());
        this.setOriginImage(peopleCount.getOriginImage());
        this.setCreateTime(peopleCount.getCreateTime());
        return this;
    }
}
