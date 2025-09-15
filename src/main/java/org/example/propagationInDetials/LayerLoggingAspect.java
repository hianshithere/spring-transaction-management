package org.example.propagationInDetials;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

  @Around("@annotation(transactional)")
  public Object logTransactionalMethod(ProceedingJoinPoint joinPoint, Transactional transactional)
      throws Throwable {

    String methodName = joinPoint.getSignature().toShortString();
    Object[] args = joinPoint.getArgs();
    boolean isNewTransaction = statusProvider.isNewTransaction();

    logger.logStart(
        "Starts Transactional",
        methodName + " Args: " + java.util.Arrays.toString(args),
        isNewTransaction);

    Object result = joinPoint.proceed();

    logger.logEnd("Ends Transactional", methodName, result);
    return result;
  }
}
