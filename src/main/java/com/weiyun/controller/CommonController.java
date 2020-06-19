package com.weiyun.controller;

import com.google.common.collect.Lists;
import com.weiyun.dto.response.AreaDTO;
import com.weiyun.entity.TbArea;
import com.weiyun.response.CommonResult;
import com.weiyun.service.TbAreaService;
import com.weiyun.service.TbThresholdService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Starix
 * @date 2020-06-19 23:31
 */
@Api(tags = "系统通用接口")
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private TbThresholdService thresholdService;
    @Autowired
    private TbAreaService areaService;


    @PostMapping(value = "/setThreshold", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CommonResult setThreshold(Integer areaCode, Integer threshold){
        thresholdService.updateThreshold(areaCode, threshold);
        return CommonResult.success();
    }

    @GetMapping(value = "/getAreaList")
    public CommonResult getAreaList(Integer areaType){
        List<TbArea> areaList = areaService.lambdaQuery().eq(TbArea::getAreaType, areaType).list();
        List<AreaDTO> areaDTOList = Lists.newArrayList();
        areaList.forEach(s -> areaDTOList.add(new AreaDTO().convertToAreaDTO(s)));
        return CommonResult.success(areaDTOList);
    }

}
