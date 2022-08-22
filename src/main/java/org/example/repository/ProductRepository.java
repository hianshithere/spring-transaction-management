package org.example.repository;

import org.example.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Repository
public class ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveProduct(Product product) {

        TransactionStatus transactionStatus = TransactionInterceptor.currentTransactionStatus();
        System.out.println("ProductRepository=>"+transactionStatus.isNewTransaction());

        String sql = "INSERT INTO PRODUCT VALUES (?,?)";
        Object[] args = {product.getId (), product.getName ()};
        jdbcTemplate.update (sql, args);
        System.out.println ("inserted:" + product);
    }
}
