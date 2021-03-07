package com.weiyun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weiyun.dto.response.StatisticsDataDTO;
import com.weiyun.entity.TbPeopleCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (TbPeopleCount)表数据库访问层
 *
 * @author Starix
 * @since 2020-06-15 12:47:30
 */
public interface TbPeopleCountMapper extends BaseMapper<TbPeopleCount> {

    IPage<TbPeopleCount> selectHistoryPeopleCountList(Page<TbPeopleCount> page,
                                                      @Param("areaCode") Integer areaCode,
                                                      @Param("startTime") Long startTime,
                                                      @Param("endTime") Long endTime);

    List<StatisticsDataDTO> selectStatisticsDataList(@Param("areaCode") Integer areaCode, @Param("interval") Integer interval);

    Map<String, Integer> selectMaxAndMinCountByAreaCode(Integer areaCode);
}