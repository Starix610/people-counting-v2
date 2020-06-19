package com.weiyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weiyun.entity.TbThreshold;

/**
 * (TbThreshold)表服务接口
 *
 * @author Starix
 * @since 2020-06-15 12:50:53
 */
public interface TbThresholdService extends IService<TbThreshold> {

    void updateThreshold(Integer areaCode, Integer threshold);
}