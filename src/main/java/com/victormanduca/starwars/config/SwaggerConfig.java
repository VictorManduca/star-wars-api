package com.victormanduca.starwars.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@OpenAPIDefinition(info = @Info(title = "StarWars API", version = "1.0", description = "API to manipulate StarWar's planet"))
public class SwaggerConfig {
}
