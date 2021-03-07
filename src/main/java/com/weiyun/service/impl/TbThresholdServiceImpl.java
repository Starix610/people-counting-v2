package com.weiyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyun.entity.TbThreshold;
import com.weiyun.mapper.TbThresholdMapper;
import com.weiyun.service.TbThresholdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * (TbThreshold)表服务实现类
 *
 * @author Starix
 * @since 2020-06-15 12:53:20
 */
@Service
public class TbThresholdServiceImpl extends ServiceImpl<TbThresholdMapper, TbThreshold> implements TbThresholdService {

    @Override
    @Transactional
    public void updateThreshold(Integer areaCode, Integer threshold) {
        TbThreshold record = this.lambdaQuery().eq(TbThreshold::getAreaCode, areaCode).one();
        if (record != null){
            record.setThreshold(threshold);
            record.setUpdateTime(new Date());
        }else {
            record = new TbThreshold();
            record.setAreaCode(areaCode);
            record.setThreshold(threshold);
        }
        this.saveOrUpdate(record);
    }
}