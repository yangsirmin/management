package com.ybc.aop;

import com.alibaba.fastjson.JSONObject;
import com.ybc.mapper.OperateLogMapper;
import com.ybc.pojo.OperateLog;
import com.ybc.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect//AOP类
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;
    @Around("@annotation(com.ybc.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.操作人id
        //获取jwt
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer id = (Integer) claims.get("id");
        //2.操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //3.操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //4.操作方法名
        String methodName = joinPoint.getSignature().getName();
        //5.操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParam = Arrays.toString(args);
        //6.方法返回值
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        String returnValue = JSONObject.toJSONString(result);
        //7.操作耗时
        long end = System.currentTimeMillis();

        Long costTime = end - start;

        OperateLog operateLog = new OperateLog(null, id, operateTime, className, methodName, methodParam, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP操作日志：{}",operateLog);
        return result;
    }

}
