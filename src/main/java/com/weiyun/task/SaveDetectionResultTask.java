package com.weiyun.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weiyun.kafka.DetectedResultConsumer;
import com.weiyun.service.TbPeopleCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 定时任务：每隔指定时间保存一次识别结果
 * @author Starix
 * @date 2020-06-14 22:42
 */
@Component
@Slf4j
public class SaveDetectionResultTask {

    @Autowired
    private TbPeopleCountService peopleCountService;


    // 每两分钟整记录一次
    @Scheduled(cron = "0 0/2 * * * ?")
    public void signIntask(){
        String faceDetectionResult = DetectedResultConsumer.getFaceDetectionResultData();
        String densityGraphicResult = DetectedResultConsumer.getDensityGraphicResultData();
        if (!StringUtils.isEmpty(faceDetectionResult)){
            log.info("========>保存检测记录faceDetectionResult: {}", DetectedResultConsumer.getFaceDetectionResultData());
            // TODO: 2020-06-15 数据落库
//            JSONObject.parseObject(faceDetectionResult, );
//            peopleCountService.
        }
        if (!StringUtils.isEmpty(densityGraphicResult)){
            log.info("========>保存检测记录densityGraphicResult: {}", DetectedResultConsumer.getFaceDetectionResultData());
            // TODO: 2020-06-15 数据落库
        }
    }

}
