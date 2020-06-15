package com.weiyun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Starix
 * @date 2020-06-14 23:35
 */
@RestController
public class TestController {

    /**
     *  普通查询：
     *      某年某月某日某小时段内人流量数据列表
     *  统计查询：
     *      某天人流量统计数据（当日内每小时人数/告警次数：平均值、峰值、最低值，当日人数/告警次数：平均、峰值、最低值）
     *      某月人流量统计数据（当月内每天人数/告警次数：平均值、峰值、最低值，当月人数/告警次数：平均值、峰值、最低值）
     *
     * @return
     */

    @GetMapping("/test")
    public String test(){
        return "sucess";
    }

}
