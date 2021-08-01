package com.finartz.restaurantApi.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAdvice {
    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    //mealController sınıfımdaki bütün methodlarda loglamayı çalıştır diyorum
    @Pointcut("execution(* com.finartz.restaurantApi.controller.MealController.*(..))")
    public void myPointCut() {
    }

    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        log.info("\nSınıf adı : " + className + "\nMethod Adı : " + methodName + "\nDeğerler : " + mapper.writeValueAsString(array));
        Object proceed = pjp.proceed();
        log.info("\nSınıf ismi : " + className + "\nMethod Adı : " + methodName + "\nGeri Dönen değer : " + mapper.writeValueAsString(proceed));
        return proceed;
    }


}
