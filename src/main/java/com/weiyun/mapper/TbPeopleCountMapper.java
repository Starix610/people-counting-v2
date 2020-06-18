package com.weiyun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weiyun.dto.request.CommonQueryConditionDTO;
import com.weiyun.entity.TbPeopleCount;

/**
 * (TbPeopleCount)表数据库访问层
 *
 * @author Starix
 * @since 2020-06-15 12:47:30
 */
public interface TbPeopleCountMapper extends BaseMapper<TbPeopleCount> {

    IPage<TbPeopleCount> selectCommonPeopleCountListData(Page<TbPeopleCount> page, CommonQueryConditionDTO commonQueryConditionReqVO);
}