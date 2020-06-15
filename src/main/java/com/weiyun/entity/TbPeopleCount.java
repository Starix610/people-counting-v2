package com.weiyun.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (TbPeopleCount)表实体类
 *
 * @author Starix
 * @since 2020-06-15 13:10:45
 */
@SuppressWarnings("serial")
@Data
@TableName
@EqualsAndHashCode(callSuper=true)
public class TbPeopleCount extends Model<TbPeopleCount> {

    @TableId(type = IdType.AUTO)
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

}