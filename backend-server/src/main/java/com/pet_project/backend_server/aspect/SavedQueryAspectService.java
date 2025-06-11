package com.pet_project.backend_server.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public interface SavedQueryAspectService {

    void pointcut();
    Object doAspect(ProceedingJoinPoint joinPoint) throws Throwable;
}
