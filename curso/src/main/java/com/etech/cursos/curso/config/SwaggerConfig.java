package com.etech.cursos.curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
        .title("EduTech Curso API")
        .version("1.0")
        .description("API para la gestión de cursos en la plataforma EduTech")
        );
    }

}
