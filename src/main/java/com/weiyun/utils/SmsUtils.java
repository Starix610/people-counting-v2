package com.weiyun.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送短信验证码工具类
 * @author Tobu
 * @date 2019/7/20 20:35
 */
@Component
public class SmsUtils {

    private static String accessKeyId;
    private static String secret;
    private static String signName;
    private static String templateCode;

    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        SmsUtils.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.secret}")
    public void setSecret(String secret) {
        SmsUtils.secret = secret;
    }

    @Value("${aliyun.sms.signName}")
    public void setSignName(String signName) {
        SmsUtils.signName = signName;
    }

    @Value("${aliyun.sms.templateCode}")
    public void setTemplateCode(String templateCode) {
        SmsUtils.templateCode = templateCode;
    }

    public static String sendSms(String telephone, String code) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        // 模板参数变量必须使用json格式的字符串，如：{"code":"0123"}
        request.putQueryParameter("TemplateParam", getParamVarJsonStr(code));
        CommonResponse response = client.getCommonResponse(request);
        String result = response.getData();
        return result;
    }


    public static String getParamVarJsonStr(String code) {
        Map<String,String> paramVarMap = new HashMap<>();
        paramVarMap.put("code", code);
        String jsonStr = JSONObject.toJSONString(paramVarMap);
        return jsonStr;
    }

}
