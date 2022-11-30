package com.wsf.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wsf
 * @since 20221027
 */
@Component
@Aspect
@Slf4j
public class TestHelloAspect {

    @Pointcut(value = "execution(* com.wsf.spring.aop.service.*.*(..))")
    //@Pointcut(value = "@annotation(com.wsf.spring.aop.aspect.HelloAspect)")
    public void pointcut() {

    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("before........");
    }

    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint) {
        log.info("after......");
    }
}
