package com.maybe.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maybe.controller.CommandController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求切面
 * 打印请求信息
 * 注意将aop配置在springMVC配置文件中 ！！！！！！（如果要拦截 Controller 的话）
 *
 * @author huoxu
 */
@Aspect
@Component
public class ControllerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);
    private static final int MAX_JSON_SIZE_ = 5000;

    /**
     * 定义拦截规则：controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.maybe.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void log() {

    }


    @Around("log()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        // 请求开始时间戳
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, String> params = new HashMap<>(16);
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    params.put(paramName, paramValue);
                }
            }
        }
        String ip = request.getHeader("X-Real-IP");
        if (ip == null) {
            ip = request.getRemoteHost();
        }
        log.info("请求url: {}, ip: {} 请求参数:\n {} ", request.getRequestURI(), ip, params,null);

        Object result = null;
        try {
            result = point.proceed();
        } catch (Exception e) {
            log.error("ERROR: ", e);
        }
        String json = toJson(result);

        if (json.length() > MAX_JSON_SIZE_) {
            json = "Data too long";
        }
        log.info("请求url: {}, 花费时间: {}ms, 回复: {}", request.getRequestURI(), System.currentTimeMillis() - start, json);
        return result;
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
