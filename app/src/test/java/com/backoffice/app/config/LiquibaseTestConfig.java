package com.backoffice.app.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


import javax.sql.DataSource;

@TestConfiguration
public class LiquibaseTestConfig {

    @Bean
    public SpringLiquibase liquibase(@Qualifier("datasource-test") DataSource dataSource) {
        var liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setDefaultSchema("test");
        liquibase.setChangeLog("classpath:/db/changelog.xml");
        return liquibase;
    }
}
