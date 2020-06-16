package com.weiyun.aspect;

import com.weiyun.common.RequestLog;
import com.weiyun.utils.IPAddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志AOP
 * @author Starix
 * @date 2020-05-03 22:31
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    // ThreadLocal记录起始时间，用于统计接口耗时，同时避免线程安全问题
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切面
     */
    @Pointcut("execution(* com.weiyun.controller.*.*(..))")
    public void log(){

    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String ip = IPAddressUtils.getIPAddr(request);
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        startTime.set(System.currentTimeMillis());
        RequestLog requestLog = new RequestLog(url, method, ip, classMethod, args);
        log.info("<=====================================================");
        log.info("[Request]: {}", requestLog);
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        log.info("[Response]: {}", result);
        log.info("Request finished, process time: {} ms", (System.currentTimeMillis() - startTime.get()));
        log.info("=====================================================>");
    }

}
