package org.example.service;

import org.example.dto.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    // Transactional should put everything under one transaction (rollback)
    public void save(Product product) {

        for (int i = 1; i <= 10; i++) {

            product.setId(i);
            product.setName("product_name_" + i);
            repository.saveProduct(product);
        }

    }

    @Transactional
    public void deleteAll() {

        repository.deleteAll();
    }
}
