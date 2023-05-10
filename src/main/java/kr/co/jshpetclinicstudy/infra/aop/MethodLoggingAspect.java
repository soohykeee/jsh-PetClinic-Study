package kr.co.jshpetclinicstudy.infra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class MethodLoggingAspect {

    @Pointcut("execution(* kr.co.jshpetclinicstudy.service.OwnerService.*(..))")
    private void ownerService() {

    }
    @Pointcut("execution(* kr.co.jshpetclinicstudy.service.PetService.*(..))")
    private void petService() {

    }
    @Pointcut("execution(* kr.co.jshpetclinicstudy.service.VetService.*(..))")
    private void vetService() {

    }
    @Pointcut("execution(* kr.co.jshpetclinicstudy.service.VisitService.*(..))")
    private void visitService() {

    }

    @Around("ownerService() || petService() || vetService() || visitService()")
    public Object logServiceTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getSimpleName();

        String methodName = joinPoint.getSignature().getName();

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object result = null;
        try {
            result = joinPoint.proceed();
            stopWatch.stop();
            log.info("[serviceTime] ClassName:{} - MethodName:{} : {}ms", className, methodName, stopWatch.getTotalTimeMillis());
        } catch (Throwable throwable) {
            // 예외 정보 로깅
            log.error("[EXCEPTION] {}-{} : {}", className, methodName, throwable.getMessage(), throwable);
            throw throwable;
        }

//        Object result = joinPoint.proceed();
//
//        stopWatch.stop();
//
//        // [serviceTime] {className}-{methodName} : {~~}ms;
//        log.info("[serviceTime] {}-{} : {}ms", className, methodName, stopWatch.getTotalTimeMillis());

        return result;
    }
}