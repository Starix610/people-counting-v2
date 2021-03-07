package com.weiyun.controller;

import com.weiyun.dto.response.DetectedResultDTO;
import com.weiyun.response.CommonResult;
import com.weiyun.service.DetectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Starix
 * @date 2021/3/7 9:04
 */
@RestController
@Api(tags= "自定义上传视频/图片检测接口")
@RequestMapping("/upload")
@CrossOrigin
public class UploadDetectController {

    @Autowired
    private DetectService detectService;


    @ApiOperation(value = "图片上传检测", notes = "接收图片，完成人流量检测并返回结果", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaType", value = "区域类型", paramType = "form", required = true),
            @ApiImplicitParam(name = "file", value = "图片文件", paramType = "form", required = true)
    })
    @PostMapping("/detect")
    public CommonResult uploadDetect(Integer areaType, MultipartFile file) throws Exception {
        if (StringUtils.isEmpty(areaType)){
            return CommonResult.validateFailed("缺少areaType参数");
        }
        //判断是否有文件上传
        if (file==null||file.getSize()<=0) {
            return CommonResult.validateFailed("文件无效");
        }
        DetectedResultDTO result = detectService.doDetect(areaType, file);
        return CommonResult.success(result);
    }

}
