package com.weiyun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weiyun.dto.request.HistoryQueryConditionDTO;
import com.weiyun.dto.request.StatisticsQueryConditionDTO;
import com.weiyun.dto.response.IndexInitDataDTO;
import com.weiyun.dto.response.StatisticsDataDTO;
import com.weiyun.entity.TbPeopleCount;

import java.util.List;

/**
 * (TbPeopleCount)表服务接口
 *
 * @author Starix
 * @since 2020-06-15 12:50:53
 */
public interface TbPeopleCountService extends IService<TbPeopleCount> {

    IPage<TbPeopleCount> queryHistoryData(HistoryQueryConditionDTO commonQueryConditionReqVO);

    List<StatisticsDataDTO> queryStatisticsData(StatisticsQueryConditionDTO conditionReqDTO);

    IndexInitDataDTO queryIndexInitData(Integer areaCode);
}