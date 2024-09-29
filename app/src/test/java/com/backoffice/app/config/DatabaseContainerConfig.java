package com.backoffice.app.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@TestConfiguration
@Testcontainers
@Slf4j
public class DatabaseContainerConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.3.0")
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("")
            .withExposedPorts(3306);

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        if (!mysql.isCreated()) {
            mysql.start();

            TestPropertyValues
                    .of(
                            "spring.datasource.jdbcUrl=".concat(mysql.getJdbcUrl().concat("?useSSL=false")),
                            "spring.datasource.username=".concat(mysql.getUsername()),
                            "spring.datasource.password=".concat(mysql.getPassword()),
                            "vote-assembly.datasource.jdbcUrl=".concat(mysql.getJdbcUrl().concat("?useSSL=false")),
                            "vote-assembly.datasource.username=".concat(mysql.getUsername()),
                            "vote-assembly.datasource.password=".concat(mysql.getPassword())
                    )
                    .applyTo(applicationContext);

            log.info("[DatabaseContainerConfig] Test container created");
        }
    }
}
