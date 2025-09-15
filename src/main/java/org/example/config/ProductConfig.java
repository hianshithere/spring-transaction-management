package org.example.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
@ComponentScan("org.example")
// Transational was implemented but to enable rollback feature we should have something of a
// basic config: EnableTransactionManagement
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ProductConfig {


    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDatasource());
    }

    @Bean
    public DataSource getDatasource() {
        return new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/springTransactions",
                "root",
                "anshit08");
    }

    @Bean
    public TransactionManager getTransactionManager() {
        return new DataSourceTransactionManager(getDatasource());
    }
}
