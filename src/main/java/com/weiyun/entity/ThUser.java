package com.weiyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (ThUser)表实体类
 *
 * @author Starix
 * @since 2020-06-15 12:47:30
 */
@SuppressWarnings("serial")
@Data
@TableName
@EqualsAndHashCode(callSuper=true)
public class ThUser extends Model<ThUser> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private Long userId;
    
    private String username;
    
    private String password;
    
    private String telephone;
    
    private String role;

}