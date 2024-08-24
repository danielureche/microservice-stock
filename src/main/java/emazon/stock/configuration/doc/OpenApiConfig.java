package emazon.stock.configuration.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Emazon Stock Microservice API",
                version = "1.0",
                description = "This API provides stock management capabilities for the Emazon e-commerce platform.",
                contact = @Contact(
                        name = "Marlon Rivera",
                        email = "riveramarlon33@gmail.com",
                        url = "https://github.com/danielureche"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Development server"),
        }
)
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Emazon Stock Microservice API")
                        .version("1.0")
                        .description("Enhanced API for Emazon stock management")
                        .termsOfService("http://emazon.com/terms/")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("API Support")
                                .url("http://emazon.com/support")
                                .email("support@emazon.com"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
