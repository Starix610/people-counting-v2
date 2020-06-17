package com.weiyun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyun.dto.request.CommonQueryConditionDTO;
import com.weiyun.entity.TbPeopleCount;

import java.util.List;

/**
 * (TbPeopleCount)表数据库访问层
 *
 * @author Starix
 * @since 2020-06-15 12:47:30
 */
public interface TbPeopleCountMapper extends BaseMapper<TbPeopleCount> {

    List<TbPeopleCount> selectCommonPeopleCountListData(CommonQueryConditionDTO commonQueryConditionReqVO);
}