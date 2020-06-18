package com.weiyun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.weiyun.dto.request.CommonQueryConditionDTO;
import com.weiyun.dto.response.PeopleCountDTO;
import com.weiyun.entity.TbPeopleCount;
import com.weiyun.mapper.TbPeopleCountMapper;
import com.weiyun.service.TbPeopleCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public IPage<PeopleCountDTO> queryCommonData(CommonQueryConditionDTO commonQueryConditionReqDTO) {
        Page<TbPeopleCount> page = new Page<>(commonQueryConditionReqDTO.getCurrent(), commonQueryConditionReqDTO.getSize());
        IPage<TbPeopleCount> iPage = peopleCountMapper.selectCommonPeopleCountListData(page, commonQueryConditionReqDTO);
        List<PeopleCountDTO> peopleCountDTOList = Lists.newArrayList();
        iPage.getRecords().forEach(s -> peopleCountDTOList.add(new PeopleCountDTO().builderPeopleCountDTO(s)));
        // TODO: 2020/6/18
        return null;
    }
}