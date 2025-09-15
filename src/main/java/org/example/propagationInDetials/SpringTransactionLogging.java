package org.example.propagationInDetials;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpringTransactionLogging {

    /*

    @Pointcut("within(org..service.*)")
    public void pointCutForLoggingService() {
    }

    @Pointcut("within(org..repository.*)")
    public void pointCutForLoggingRepo() {
    }

    @Around("pointCutForLoggingService()")
    public Object logBeforeMethodService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodSignatureName = proceedingJoinPoint.getSignature().getName();
        System.out.println("service method starting: " + methodSignatureName);

        TransactionStatus transactionStatus = TransactionInterceptor.currentTransactionStatus();
        System.out.println("Is New Transaction: " + transactionStatus.isNewTransaction());

        Object returnValue = proceedingJoinPoint.proceed();
        System.out.println(returnValue);
        System.out.println("service method completed:" + methodSignatureName);

        return returnValue;
    }

    @Around("pointCutForLoggingRepo()")
    public Object logBeforeMethodRepo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodSignatureName = proceedingJoinPoint.getSignature().getName();
        System.out.println("repository method starting:" + methodSignatureName);

        TransactionStatus transactionStatus = TransactionInterceptor.currentTransactionStatus();
        System.out.println("Is New Transaction: " + transactionStatus.isNewTransaction());

        Object returnValue = proceedingJoinPoint.proceed();
        System.out.println(returnValue);
        System.out.println("repository method completed:" + methodSignatureName);

        return returnValue;
    }

    */
}
