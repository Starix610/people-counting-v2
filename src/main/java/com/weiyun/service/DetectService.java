package com.weiyun.service;

import com.weiyun.dto.response.DetectedResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Starix
 * @date 2021/3/7 9:09
 */
public interface DetectService {
    DetectedResultDTO doDetect(Integer areaType, MultipartFile pictureFile) throws IOException, InterruptedException;
}
