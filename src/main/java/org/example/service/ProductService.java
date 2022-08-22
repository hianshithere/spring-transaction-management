package org.example.service;

import org.example.dto.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    // Transactional should put everything under one transaction (rollback)
    public void save(Product product) {

        TransactionStatus transactionStatus = TransactionInterceptor.currentTransactionStatus();
        System.out.println(transactionStatus.isNewTransaction());

        for (int i = 1; i <= 10; i++) {
            product.setId(i);
            product.setName("product_name_" + i);
            repository.saveProduct(product);
            if (i == 7) {
                // ACID properties are not followed
                // not a rollback -> which is what we want
                // A.T.O.M.I.C
                throw new RuntimeException("there is some error...");
            }

        }

    }
}
