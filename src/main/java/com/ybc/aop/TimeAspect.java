//package com.ybc.aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Order(1)//可以通过此注解来控制多个AOP类执行的顺序
//@Component
//@Slf4j
//@Aspect//AOP类
//public class TimeAspect {
//    //抽取切入点表达式
//    @Pointcut("execution(* com.ybc.service.*.*(..))")
//    public void pt(){}
//    //通知类型
//    @Around("pt()")
//    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        //记录开始时间
//        long start = System.currentTimeMillis();
//        //运行原始数据
//        Object result = joinPoint.proceed();
//        //记录结束时间
//        long end = System.currentTimeMillis();
//        log.info("运行时间为：" + (end - start));
//        return result;
//    }
//}
