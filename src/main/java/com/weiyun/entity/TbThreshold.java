package com.weiyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (TbThreshold)表实体类
 *
 * @author Starix
 * @since 2020-06-15 12:47:30
 */
@SuppressWarnings("serial")
@Data
@TableName
@EqualsAndHashCode(callSuper=true)
public class TbThreshold extends Model<TbThreshold> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //区域代码
    private Integer areaCode;
    //人数阈值
    private Integer threshold;

}