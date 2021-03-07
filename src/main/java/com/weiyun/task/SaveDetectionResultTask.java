package com.weiyun.task;

import com.alibaba.fastjson.JSONObject;
import com.weiyun.entity.TbArea;
import com.weiyun.netty.utils.WebSocketUtil;
import com.weiyun.service.TbAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 定时任务：每隔指定时间保存一次识别结果
 * @author Starix
 * @date 2020-06-14 22:42
 */
@Component
@Slf4j
public class SaveDetectionResultTask {

    private static String faceDetectionResultData = "";

    // TODO: 2020-06-19 模拟人流量检测结果推送时用到，后续需要删除
    private static List<TbArea> areaList;

    @Autowired
    private TbAreaService areaService;

    @PostConstruct
    public void initThreshold(){
        areaList = areaService.lambdaQuery().list();
        log.info("当前areaList: {}", areaList);
    }


    // 每两分钟整记录一次
    // @Scheduled(cron = "0 0/2 * * * ?")
    public void signIntask(){
        // TODO: 2020-06-20 此处模拟两分钟数据落库与推送
        JSONObject jsonObject = JSONObject.parseObject(faceDetectionResultData);
        jsonObject.put("chart_display", true);
        WebSocketUtil.sendMessageToAll(jsonObject.toJSONString());
        log.info("========>保存检测记录faceDetectionResult: {}", jsonObject.toJSONString());

        // String faceDetectionResult = DetectedResultConsumer.getFaceDetectionResultData();
        // String densityGraphicResult = DetectedResultConsumer.getDensityGraphicResultData();
        // if (!StringUtils.isEmpty(faceDetectionResult)){
        //     log.info("========>保存检测记录faceDetectionResult: {}", DetectedResultConsumer.getFaceDetectionResultData());
        //     // TODO: 2020-06-15 数据落库
        //     // JSONObject jsonObject = JSONObject.parseObject(faceDetectionResult);
        // }
        // if (!StringUtils.isEmpty(densityGraphicResult)){
        //     log.info("========>保存检测记录densityGraphicResult: {}", DetectedResultConsumer.getFaceDetectionResultData());
        //     // TODO: 2020-06-15 数据落库
        // }
    }


    // 模拟每两秒推送一次检测结果数据
    @Scheduled(fixedRate = 2000)
    public void websocketDataMock() {
        for (TbArea area : areaList) {
            //随机人数
            Integer detectedCount = new Random().nextInt(500);
            String image = "https://images.unsplash.com/photo-1592107761705-30a1bbc641e7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=934&q=80";
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("area_code", area.getAreaCode());
            jsonObject.put("detected_count", detectedCount);
            jsonObject.put("original_image", image);
            jsonObject.put("detected_image", image);
            jsonObject.put("time", time);
            if (jsonObject.getInteger("detected_count") >= area.getThreshold()){
                // 标记超出阈值
                jsonObject.put("overflow", true);
            }
            faceDetectionResultData = jsonObject.toJSONString();
            WebSocketUtil.sendMessageToAll(faceDetectionResultData);
            //log.info("Topic: face_detection, Message: {}", faceDetectionResultData);
        }
    }

    public void updateThreshold() {
        initThreshold();
        log.info("areaList更新，当前值: {}", areaList);
    }

}
