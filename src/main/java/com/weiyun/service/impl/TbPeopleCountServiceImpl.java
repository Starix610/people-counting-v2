package com.weiyun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyun.dto.request.CommonQueryConditionDTO;
import com.weiyun.entity.TbPeopleCount;
import com.weiyun.mapper.TbPeopleCountMapper;
import com.weiyun.service.TbPeopleCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public IPage<TbPeopleCount> queryCommonData(CommonQueryConditionDTO conditionReqDTO) {
        Date startDate = conditionReqDTO.builderStartDate();
        Date endDate = conditionReqDTO.builderEndDate();
        Page<TbPeopleCount> page = new Page<>(conditionReqDTO.getCurrent(), conditionReqDTO.getSize());
        IPage<TbPeopleCount> iPage = peopleCountMapper.selectCommonPeopleCountListData(page, startDate, endDate);
        return iPage;
    }
}