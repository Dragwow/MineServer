package com.pet_project.backend_server.aspect.impl;

import com.pet_project.backend_server.aspect.SavedQueryAspectService;
import com.pet_project.backend_server.service.SavedQueryService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
@AllArgsConstructor
public class SavedQueryAspectServiceImpl implements SavedQueryAspectService {

    private final SavedQueryService savedQueryService;

    @Override
    @Pointcut("execution(* com.pet_project.backend_server.service.impl.searchImpl.OfferSearchServiceImpl.searchByStatus(..)) || " +
            "execution(* com.pet_project.backend_server.service.impl.searchImpl.OfferSearchServiceImpl.searchByType(..)) ||" +
            "execution(* com.pet_project.backend_server.service.impl.searchImpl.OfferSearchServiceImpl.search(..)) ||" +
            "execution(* com.pet_project.backend_server.service.impl.searchImpl.UserProfileSearchServiceImpl.searchProfile(..))"
    )
    public void pointcut() { }

    @Override
    @Around(value = "pointcut()")
    public Object doAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] param = joinPoint.getArgs();
        if (ArrayUtils.isNotEmpty(param)) {
            String searchText = (String) param[0];
            savedQueryService.save(searchText);
        }
        return joinPoint.proceed();
    }
}
