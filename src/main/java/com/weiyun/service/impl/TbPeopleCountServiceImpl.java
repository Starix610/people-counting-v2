package com.weiyun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyun.dto.request.HistoryQueryConditionDTO;
import com.weiyun.dto.request.StatisticsQueryConditionDTO;
import com.weiyun.dto.response.IndexInitDataDTO;
import com.weiyun.dto.response.StatisticsDataDTO;
import com.weiyun.entity.TbPeopleCount;
import com.weiyun.mapper.TbPeopleCountMapper;
import com.weiyun.service.TbPeopleCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * (TbPeopleCount)表服务实现类
 *
 * @author Starix
 * @since 2020-06-15 12:53:18
 */
@Service
@Slf4j
public class TbPeopleCountServiceImpl extends ServiceImpl<TbPeopleCountMapper, TbPeopleCount> implements TbPeopleCountService {

    @Autowired
    private TbPeopleCountMapper peopleCountMapper;

    @Override
    public IPage<TbPeopleCount> queryHistoryData(HistoryQueryConditionDTO conditionReqDTO) {
        Integer areaCode = conditionReqDTO.getAreaCode();
        Long startTime = conditionReqDTO.getStartTime();
        Long endTime = conditionReqDTO.getEndTime();
        Page<TbPeopleCount> page = new Page<>(conditionReqDTO.getCurrent(), conditionReqDTO.getSize());
        IPage<TbPeopleCount> iPage = peopleCountMapper.selectHistoryPeopleCountList(page, areaCode, startTime, endTime);
        return iPage;
    }

    @Override
    public List<StatisticsDataDTO> queryStatisticsData(StatisticsQueryConditionDTO conditionReqDTO) {
        return peopleCountMapper.selectStatisticsDataList(conditionReqDTO.getAreaCode(), conditionReqDTO.getInterval());
    }


    @Override
    public IndexInitDataDTO queryIndexInitData(Integer areaCode) {
        Map<String, Integer> maxMinCount = peopleCountMapper.selectMaxAndMinCountByAreaCode(areaCode);
        List<StatisticsDataDTO> statisticsDataList = peopleCountMapper.selectStatisticsDataList(areaCode, 7);
        IndexInitDataDTO indexInitDataDTO = new IndexInitDataDTO();
        indexInitDataDTO.setAreaCode(areaCode);
        indexInitDataDTO.setMaxCount(maxMinCount == null ? 0 : maxMinCount.get("maxCount"));
        indexInitDataDTO.setMinCount(maxMinCount == null ? 0 : maxMinCount.get("minCount"));
        indexInitDataDTO.setHistoryData(statisticsDataList);
        return indexInitDataDTO;
    }
}