package com.weiyun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * (TbThreshold)表实体类
 *
 * @author Tobu
 * @since 2020-06-20 00:14:34
 */
@SuppressWarnings("serial")
@Data
@TableName
@EqualsAndHashCode(callSuper=true)
public class TbThreshold extends Model<TbThreshold> {
    
    private Integer id;
    //区域代码
    private Integer areaCode;
    //人数阈值
    private Integer threshold;
    //更新时间
    private Date updateTime;

}