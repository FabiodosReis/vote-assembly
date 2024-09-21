package com.backoffice.app.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Value("${spring.application.name}")
    private String application;

    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title(application)
                        .version("v1")
                        .description("Rest API vote assembly")
                );
    }
}
