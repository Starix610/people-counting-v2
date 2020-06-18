package com.weiyun.task;

import com.alibaba.fastjson.JSONObject;
import com.weiyun.entity.TbThreshold;
import com.weiyun.kafka.DetectedResultConsumer;
import com.weiyun.netty.common.WebSocketUtil;
import com.weiyun.service.TbPeopleCountService;
import com.weiyun.service.TbThresholdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Random;

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

    // TODO: 2020-06-19 模拟人流量检测结果推送时用到，后续需要删除
    private static Integer threshold;
    @Autowired
    private TbThresholdService thresholdService;

    @PostConstruct
    public void initThreshold(){
        // TODO: 2020-06-15 暂时只处理一个区域的一个阈值，后续可能需要重构
        TbThreshold th = thresholdService.lambdaQuery().select(TbThreshold::getThreshold).one();
        threshold = th.getThreshold();
        log.info("当前threshold: {}", threshold);
    }


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


    // 模拟每两秒推送一次检测结果数据
    @Scheduled(fixedRate = 2000)
    public void websocketDataMock(){
        //随机人数
        Integer detectedCount = new Random().nextInt(500);
        String image = "https://images.unsplash.com/photo-1592107761705-30a1bbc641e7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=934&q=80";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detected_count", detectedCount);
        jsonObject.put("original_image", image);
        jsonObject.put("detected_image", image);
        if (jsonObject.getInteger("detected_count") >= threshold){
            // 标记超出阈值
            jsonObject.put("overflow", true);
        }
        log.info("Topic: face_detection, Message: {}", jsonObject.toJSONString());
        WebSocketUtil.sendMessageToAll(jsonObject.toJSONString());
    }

}
