package com.weiyun.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weiyun.entity.TbThreshold;
import com.weiyun.netty.common.WebSocketUtil;
import com.weiyun.service.TbThresholdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * @author Starix
 * @date 2020-06-07 19:44
 */
//@Component
@Slf4j
public class DetectedResultConsumer {


    // 保存当前检测结果到本地变量中，方便后续做定时保存
    private static String faceDetectionResultData = "";

    private static String densityGraphicResultData = "";

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


    @KafkaListener(topics = {"face_detection"})
    public void faceDetectionResult(ConsumerRecord<?, ?> record, Acknowledgment ack){
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()){
            String msg = message.get().toString();
            JSONObject jsonObject = JSON.parseObject(msg);
            if (jsonObject.getInteger("detected_count") >= threshold){
                // 标记超出阈值
                jsonObject.put("overflow", true);
            }
            log.info("Topic: face_detection, Offset: {}, Message: {}", record.offset(), jsonObject.toJSONString());
            faceDetectionResultData = jsonObject.toJSONString();
            WebSocketUtil.sendMessageToAll(faceDetectionResultData);
            ack.acknowledge();
        }
    }


    @KafkaListener(topics = {"density_graphic"})
    public void densityGraphicResult(ConsumerRecord<?, ?> record, Acknowledgment ack){
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()){
            String msg = message.get().toString();
            JSONObject jsonObject = JSON.parseObject(msg);
            if (jsonObject.getInteger("detected_count") >= threshold){
                // 标记超出阈值
                jsonObject.put("overflow", true);
            }
            log.info("Topic: density_graphic, Offset: {}, Message: {}", record.offset(), jsonObject.toJSONString());
            densityGraphicResultData = jsonObject.toJSONString();
            WebSocketUtil.sendMessageToAll(densityGraphicResultData);
            ack.acknowledge();
        }
    }


    public static String getFaceDetectionResultData() {
        return faceDetectionResultData;
    }

    public static String getDensityGraphicResultData() {
        return densityGraphicResultData;
    }

}
