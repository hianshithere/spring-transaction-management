package org.example.propagationInDetials;

import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Component
public class TransactionStatusProvider {
    public boolean isNewTransaction() {
        TransactionStatus status = TransactionInterceptor.currentTransactionStatus();
        return status != null && status.isNewTransaction();
    }
}
