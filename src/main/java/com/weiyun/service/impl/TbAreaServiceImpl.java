package com.weiyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyun.mapper.TbAreaMapper;
import com.weiyun.entity.TbArea;
import com.weiyun.service.TbAreaService;
import com.weiyun.task.SaveDetectionResultTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (TbArea)表服务实现类
 *
 * @author Starix
 * @since 2020-06-15 12:53:17
 */
@Service
public class TbAreaServiceImpl extends ServiceImpl<TbAreaMapper, TbArea> implements TbAreaService {

    @Autowired
    private TbAreaMapper areaMapper;
    @Autowired
    private SaveDetectionResultTask saveDetectionResultTask;

    @Override
    public void udpateAreaThreshold(Integer areaCode, Integer threshold) {
        TbArea area = new TbArea();
        area.setAreaCode(areaCode);
        area.setThreshold(threshold);
        areaMapper.updateById(area);
        saveDetectionResultTask.updateThreshold();
    }
}