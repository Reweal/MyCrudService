package com.example.myservice.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut("execution(* com.example.myservice.service.user.UserService.*(..))")
    public void allServiceMethods() {}
}
