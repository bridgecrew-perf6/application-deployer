package com.kovalenko.deployer.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(getInfo());
    }

    private Info getInfo() {
        return new Info()
            .title("REST API")
            .description("REST API Swagger open api documentation")
            .version("1.0")
            .contact(new Contact().name("Kovalenko Dmytro").email("apach.dima@gmail.com"))
            .license(new License().url("https://www.apache.org/licenses/LICENSE-2.0").name("Apache 2.0"));
    }
}
