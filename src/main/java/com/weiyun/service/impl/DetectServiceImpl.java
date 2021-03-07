package com.weiyun.service.impl;

import com.weiyun.dto.response.DetectedResultDTO;
import com.weiyun.exception.CustomException;
import com.weiyun.response.CommonResult;
import com.weiyun.service.DetectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Starix
 * @date 2021/3/7 9:09
 */
@Service
@Slf4j
public class DetectServiceImpl implements DetectService {

    private final static String UPLOAD_FILE_PATH = "E:\\upload\\";
    private final static String RESULT_FILE_PATH = "E:\\upload\\detect_result\\";
    private final static String PYTHON_MODEL_PATH_FACE_DETECT = "D:\\people_count_detect\\face_recognition";
    private final static String PYTHON_MODEL_PATH_DENSITY_GRAPHIC = "D:\\people_count_detect\\density_graphic";
    private final static String FILE_URL = "http://www.starix.top:9901/images/";


    @Override
    public DetectedResultDTO doDetect(Integer areaType, MultipartFile file) throws IOException, InterruptedException {

        long startTime = System.currentTimeMillis();
        //获取文件后缀名(不带.)
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = startTime + "."+ ext;
        String detectedFileName = startTime + "_d."+ ext;
        //图片绝对路径
        String filePath = UPLOAD_FILE_PATH + fileName;
        //输出路劲
        String detectedFilePath = RESULT_FILE_PATH + detectedFileName;
        //上传的图片写到指定位置
        file.transferTo(new File(filePath));
        String[] cmd = buildCommand(areaType, filePath, detectedFilePath);
        String pythonModelPath = areaType == 2 ? PYTHON_MODEL_PATH_FACE_DETECT : PYTHON_MODEL_PATH_DENSITY_GRAPHIC;
        Process process = Runtime.getRuntime().exec(cmd,null, new File(pythonModelPath));
        int status = process.waitFor();
        if (status != 0){
            throw new CustomException(CommonResult.failed("检测出错，请稍后重试"));
        }
        InputStream in = process.getInputStream();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(in));
        String line;
        Integer result = null;
        while ((line = buffReader.readLine()) != null){
            if (line.startsWith("count")){
                result = Integer.parseInt(line.split(": ")[1]);
            }
        }

        if (result == null){
            throw new CustomException(CommonResult.failed("检测出错，请稍后重试"));
        }

        //简单关闭流，暂时不考虑异常
        buffReader.close();
        in.close();

        long endTime = System.currentTimeMillis();
        DetectedResultDTO detectedResultDTO = new DetectedResultDTO();
        detectedResultDTO.setOriginImage(FILE_URL + fileName);
        detectedResultDTO.setDetectedImage(FILE_URL + "detect_result/" +detectedFileName);
        detectedResultDTO.setDetectedCount(result);
        detectedResultDTO.setTime((int) (endTime - startTime));

        return detectedResultDTO;
    }

    private String[] buildCommand(Integer areaType, String filePath, String detectedFilePath){
        if (areaType == 0 || areaType == 1){
            String[] cmd = new String[8];
            cmd[0] = "python";
            cmd[1] = "detect.py";
            cmd[2] = "--weights";
            cmd[3] = areaType == 0 ? "utils/intensive_weights.h5" : "utils/sparse_weights.h5";
            cmd[4] = "--image";
            cmd[5] = filePath;
            cmd[6] = "--result";
            cmd[7] = detectedFilePath;
            return cmd;
        } else {
            String[] cmd = new String[10];
            cmd[0] = "python";
            cmd[1] = "detect.py";
            cmd[2] = "--test_device";
            cmd[3] = "cpu";
            cmd[4] = "--threshold";
            cmd[5] = "0.65";
            cmd[6] = "--image";
            cmd[7] = filePath;
            cmd[8] = "--result";
            cmd[9] = detectedFilePath;
            return cmd;
        }
    }
}
