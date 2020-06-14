package com.weiyun.exception;

import com.weiyun.response.CommonResult;
import com.weiyun.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class BaseExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    //处理其它异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handlerAny(Exception e){
        logger.error("系统内部异常:[{}]",e.getMessage(), e);
        return CommonResult.failed(ResultCode.SERVER_ERROR);
    }

    //处理自定义的业务异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public CommonResult handlerCustomException(Exception e){
        CustomException customException = (CustomException) e;
        CommonResult result = customException.getCommonResult();
        logger.error("出现业务异常,code:{},msg:{}",result.getCode(), result.getMessage());
        return result;
    }

    //处理"请求方法不支持"异常
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public CommonResult handlerMethodNotSupported(HttpServletRequest request, Exception e){
        logger.error("不支持的请求方法:[{}]",request.getMethod());
        return CommonResult.failed(ResultCode.VALIDATE_FAILED, "请求方法不支持");
    }


    //处理"Content-Type不支持"异常
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResult handlerContentTypeSupported(HttpServletRequest request, Exception e){
        logger.error("不支持的Content-Type:[{}]",request.getContentType());
        return CommonResult.failed(ResultCode.VALIDATE_FAILED, "请求的Content-Type不支持");
    }


    //处理"MessageNotReadable"异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResult handlerMessageNotReadable(Exception e){
        logger.error("请求数据格式不正确:{}", e.getMessage());
        return CommonResult.failed(ResultCode.VALIDATE_FAILED, "请求的数据格式不对");
    }

    //处理"MethodArgumentTypeMismatchException"异常
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResult handlerMethodArgumentTypeMismatch(Exception e){
        logger.error("请求参数类型不正确:{}", e.getMessage());
        return CommonResult.failed(ResultCode.VALIDATE_FAILED, "请求参数类型错误");
    }

    //处理"MissingServletRequestParameterException"异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResult handlerMissingRequestParameter(Exception e){
        logger.error("请求参数缺失:{}", e.getMessage());
        return CommonResult.failed(ResultCode.VALIDATE_FAILED, "请求参数缺失");
    }

    //捕获全局ConstraintViolationException异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResult handlerMissingRequestParameter(ConstraintViolationException e) {
        logger.error("请求参数缺失:{}", e.getMessage());
        StringBuffer sb = new StringBuffer();
        for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
            sb.append(constraintViolation.getMessage() + ",");
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED, sb.toString());
    }

    //捕获全局ConstraintViolationException异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResult handlerMissingRequestParameter(MethodArgumentNotValidException e) {
        logger.error("请求参数缺失:{}", e.getMessage());
        Map<String, String> map = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        allErrors.forEach(o -> map.put(o.getField(), o.getDefaultMessage()));
        return CommonResult.failed(ResultCode.VALIDATE_FAILED, map.toString());
    }
}
