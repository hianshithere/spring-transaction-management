package org.example;

import org.example.config.ProductConfig;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        ConfigurableApplicationContext
                configurableApplicationContext =
                new AnnotationConfigApplicationContext(ProductConfig.class);

        configurableApplicationContext.registerShutdownHook();
        System.out.println("== registered shutdown hook ==");
        configurableApplicationContext.close();

        assertFalse("If this fails context is still active!",
                configurableApplicationContext.isActive());

    }
}
