package com.weiyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weiyun.dto.request.CommonQueryConditionDTO;
import com.weiyun.dto.response.PeopleCountDTO;
import com.weiyun.entity.TbPeopleCount;

import java.util.List;

/**
 * (TbPeopleCount)表服务接口
 *
 * @author Starix
 * @since 2020-06-15 12:50:53
 */
public interface TbPeopleCountService extends IService<TbPeopleCount> {

    List<PeopleCountDTO> queryCommonData(CommonQueryConditionDTO commonQueryConditionReqVO);
}