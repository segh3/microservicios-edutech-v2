package com.etech.evaluaciones.evaluacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ConfigSwagger {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
        .title("edutech Evaluacion API")
        );
    }
}
