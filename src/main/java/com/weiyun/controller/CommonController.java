package com.weiyun.controller;

import com.google.common.collect.Lists;
import com.weiyun.dto.response.AreaDTO;
import com.weiyun.entity.TbArea;
import com.weiyun.response.CommonResult;
import com.weiyun.response.ResultCode;
import com.weiyun.service.TbAreaService;
import com.weiyun.service.TbPeopleCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Starix
 * @date 2020-06-19 23:31
 */
@Api(tags = "系统通用接口")
@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonController {

    @Autowired
    private TbAreaService areaService;
    @Autowired
    private TbPeopleCountService peopleCountService;


    @GetMapping(value = "/getThreshold")
    @ApiOperation(value = "获取当前阈值",notes = "获取某一区域的人数阈值")
    @ApiImplicitParam(name = "areaCode", value = "区域id", paramType = "query", dataType = "int", required = true)
    public CommonResult getThreshold(Integer areaCode){
        Integer threshold = areaService.lambdaQuery().eq(TbArea::getAreaCode, areaCode).one().getThreshold();
        return CommonResult.success(threshold);
    }


    @PostMapping(value = "/setThreshold", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "设置阈值",notes = "设置某一区域的人数阈值，需要先选择区域，再设置值。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaCode", value = "区域id", paramType = "form", dataType = "int", required = true),
            @ApiImplicitParam(name = "threshold", value = "人数阈值", paramType = "form", dataType = "int", required = true)
    })
    public CommonResult setThreshold(Integer areaCode, Integer threshold){
        areaService.udpateAreaThreshold(areaCode, threshold);
        return CommonResult.success();
    }

    @GetMapping(value = "/getAreaList")
    @ApiOperation(value = "获取区域列表",notes = "根据areaType获取区域名称列表，areaType是固定的，" +
            "定义为：0-室外密集型，1-室外稀疏型，2-正面可见型。在前端固定显示这三个选项，" +
            "根据用户选择项来查询对应areaType的区域名称列表")
    @ApiImplicitParam(name = "areaType", value = "区域类型",paramType = "query", dataType = "int", required = true)
    public CommonResult getAreaList(Integer areaType){
        List<TbArea> areaList = areaService.lambdaQuery().eq(TbArea::getAreaType, areaType).list();
        List<AreaDTO> areaDTOList = Lists.newArrayList();
        areaList.forEach(s -> areaDTOList.add(new AreaDTO().convertToAreaDTO(s)));
        return CommonResult.success(areaDTOList);
    }

    @GetMapping(value = "/indexInitData")
    @ApiOperation(value = "获取首页初始数据",notes = "获取当日人数最大值/最小值/近七日数据")
    @ApiImplicitParam(name = "areaCode", value = "区域id", paramType = "query", dataType = "int", required = true)
    public CommonResult getIndexInitData(Integer areaCode){
        return CommonResult.success(peopleCountService.queryIndexInitData(areaCode));
    }



    @PostMapping(value = "/createNewArea", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "新增监测区域",notes = "新增监测区域")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaType", value = "区域类型", paramType = "form", dataType = "int", required = true),
            @ApiImplicitParam(name = "areaName", value = "区域名称", paramType = "form", dataType = "string", required = true),
            @ApiImplicitParam(name = "threshold", value = "初始阈值", paramType = "form", dataType = "int", required = true)
    })
    public CommonResult createNewArea(Integer areaType, String areaName, Integer threshold){
        if (areaType == null || StringUtils.isEmpty(areaName) || threshold == null){
            return CommonResult.failed(ResultCode.VALIDATE_FAILED,"参数不能为空");
        }
        TbArea area = new TbArea();
        area.setAreaType(areaType);
        area.setAreaName(areaName);
        area.setThreshold(threshold);
        area.setCreateTime(new Date());
        areaService.save(area);
        return CommonResult.success();
    }
}
