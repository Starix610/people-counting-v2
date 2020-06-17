package com.weiyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public List<PeopleCountDTO> queryCommonData(CommonQueryConditionDTO commonQueryConditionReqVO) {
        List<TbPeopleCount> peopleCountList = peopleCountMapper.selectCommonPeopleCountListData(commonQueryConditionReqVO);
        System.out.println(peopleCountList);
        return null;
    }
}