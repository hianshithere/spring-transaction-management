package org.example.propagationInDetials;

import org.springframework.stereotype.Service;

@Service
public class TransactionLogger {
    public void logStart(String layer, String methodName, boolean isNewTransaction) {
        System.out.printf("%s method starting: %s | Is New Transaction: %b%n", layer, methodName, isNewTransaction);
    }

    public void logEnd(String layer, String methodName, Object returnValue) {
        System.out.printf("%s method completed: %s | Return Value: %s%n", layer, methodName, returnValue);
    }
}
