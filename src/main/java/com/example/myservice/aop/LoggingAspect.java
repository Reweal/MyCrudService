package com.example.myservice.aop;

import com.example.myservice.model.User;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

//    @Before("PointCuts.allServiceMethods()") -- выполняет код до вызова метода
//    @Around("PointCuts.allServiceMethods()") -- более гибкий, выполняет код до и после вызова метода,
//    а также может изменять параметры и результат метода
//    @AfterReturning -- выполняет код после успешного возврата из метода // org.aspectj.lang.annotation.*
//    @AfterThrowing -- выполняет код после выброса исключения в методе // org.aspectj.lang.annotation.*

    @After("PointCuts.allServiceMethods()") // -- выполняет код после выполнения метода
    public void logServiceMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Method executed: " + methodName);
        Object[] arguments = joinPoint.getArgs();
        for (Object arg: arguments) {
            if (arg instanceof User && joinPoint.getSignature().getName().startsWith("add")) {
                User user = (User) arg;
                log.info("Попытка добавить user с именем " + user.getFirstName());
            } else if (arg instanceof User && joinPoint.getSignature().getName().startsWith("upd")) {
                User user = (User) arg;
                log.info("Попытка обновить user с именем " + user.getFirstName());
            }
        }
    }
}
