package org.example;

import org.example.config.ProductConfig;
import org.example.dto.Product;
import org.example.service.ProductService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext (
                ProductConfig.class
        );

        context.registerShutdownHook ();

        ProductService productService = context.getBean (
                "productService",
                ProductService.class);

        productService.save (new Product ());
        context.close ();
    }
}

