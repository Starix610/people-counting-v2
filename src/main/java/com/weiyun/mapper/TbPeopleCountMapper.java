package com.weiyun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weiyun.entity.TbPeopleCount;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * (TbPeopleCount)表数据库访问层
 *
 * @author Starix
 * @since 2020-06-15 12:47:30
 */
public interface TbPeopleCountMapper extends BaseMapper<TbPeopleCount> {

    IPage<TbPeopleCount> selectCommonPeopleCountListData(Page<TbPeopleCount> page, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}