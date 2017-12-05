package org.tustcs.eztable.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private static final Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(* org.tustcs.eztable.controller.*.*(..))")
    public void req(){

    }

    @Before("req()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        //url
        logger.info("url={}",request.getRequestURL());

        //http_method
        logger.info("http_method={}",request.getMethod());

        //ip
        logger.info("ip={}",request.getRemoteAddr());

        //class_method
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

        //params
        logger.info("params={}",joinPoint.getArgs());
    }

    @AfterReturning(returning = "object",pointcut = "req()")
    public void doAfterReturning(Object object){
       // logger.info("response={}",object.toString());
    }
}
