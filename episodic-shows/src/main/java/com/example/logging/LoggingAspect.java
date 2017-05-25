package com.example.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class LoggingAspect {

//    @Before("allUserRoutes()")
    @Before("execution(* get())")
    public void LoggingAdvice() {
        System.out.println("User controller route reached");
    }

    @Before("allCreates()")
    public void CreateAdvice() {
        System.out.println("Entity created");
    }

    @Pointcut("within(com.example.episodicusers.User)")
    public void allUserRoutes() {}

    @Pointcut("execution(* create(..))")
    public void allCreates() {}
}
