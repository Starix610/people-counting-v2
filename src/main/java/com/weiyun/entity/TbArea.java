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
 * (TbArea)表实体类
 *
 * @author Starix
 * @since 2020-06-15 12:47:26
 */
@SuppressWarnings("serial")
@Data
@TableName
@EqualsAndHashCode(callSuper=true)
public class TbArea extends Model<TbArea> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //区域代码
    private Integer areaCode;
    //区域名称
    private String areaName;
    //区域类型：0-室外密集型，1-室内密集型，2-室内人脸正面可见
    private Integer areaType;
    //创建时间
    private Date createTime;

}