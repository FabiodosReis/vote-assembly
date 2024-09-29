package com.backoffice.app.annotation;

import com.backoffice.app.AppApplication;
import com.backoffice.app.config.DataSourceTestConfig;
import com.backoffice.app.config.DatabaseContainerConfig;
import com.backoffice.app.config.LiquibaseTestConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                DatabaseContainerConfig.class, DataSourceTestConfig.class, LiquibaseTestConfig.class,
                AppApplication.class
        },
        initializers = {DatabaseContainerConfig.class}
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public @interface TestRepositoryCommonAnnotation {
}
