package org.example.propagationInDetials;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LayerLoggingAspect {

    private final TransactionLogger logger;
    private final TransactionStatusProvider statusProvider;

    public LayerLoggingAspect(TransactionLogger logger, TransactionStatusProvider statusProvider) {
        this.logger = logger;
        this.statusProvider = statusProvider;
    }

    @Pointcut("within(org..service..*)")
    public void serviceLayer() {}

    @Pointcut("within(org..repository..*)")
    public void repositoryLayer() {}

    @Around("serviceLayer()")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logMethod(joinPoint, "Service");
    }

    @Around("repositoryLayer()")
    public Object logRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        return logMethod(joinPoint, "Repository");
    }

    private Object logMethod(ProceedingJoinPoint joinPoint, String layer) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        boolean isNewTransaction = statusProvider.isNewTransaction();
        logger.logStart(layer, methodName, isNewTransaction);

        Object returnValue = joinPoint.proceed();

        logger.logEnd(layer, methodName, returnValue);
        return returnValue;
    }
}
