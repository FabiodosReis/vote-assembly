package com.backoffice.app.infraestructure.datasource;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.PersistenceUnit;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.backoffice.app")
@EnableJpaRepositories(
        basePackages = "com.backoffice.app",
        transactionManagerRef = "jdbcTransactionManager"
)
public class DataSourceConfig {


    @Bean
    @ConfigurationProperties(prefix = "vote-assembly.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @PersistenceUnit(name = "vote-assembly-pu")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(adapter);

        factoryBean.setDataSource(dataSource());
        factoryBean.setPersistenceUnitName("vote-assembly-pu");
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.hbm2ddl", "none");
        factoryBean.setJpaProperties(properties);

        factoryBean.setPackagesToScan("com.backoffice.app");

        return factoryBean;
    }

    @Primary
    @Bean("jdbcTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new JdbcTransactionManager(dataSource());
    }

    /*@Bean
    public TransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }*/

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }
}
