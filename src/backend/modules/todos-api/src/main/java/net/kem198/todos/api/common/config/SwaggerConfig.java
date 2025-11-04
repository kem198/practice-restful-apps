package net.kem198.todos.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI todosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("KeM's Todos API")
                        .description("""
                                REST API documentation for KeM's Todos.

                                - [GitHub Repository](https://github.com/kem198/kems-todos)
                                - [Swagger UI](https://swagger.io/tools/swagger-ui/)
                                """)
                        .version("v0.1.0")
                // .contact(new Contact()
                // .name("KeM198")
                // .url("https://github.com/kem198")
                // .email("example@example.com"))
                // .license(new License()
                // .name("Apache 2.0")
                // .url("https://www.apache.org/licenses/LICENSE-2.0")))
                // .externalDocs(new ExternalDocumentation()
                // .description("GitHub Repository")
                // .url("https://github.com/kem198/kems-todos")
                );
    }
}
