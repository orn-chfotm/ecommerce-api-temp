package com.build.ecommerce.core.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI eCommerceAppOpenApi() {
        return new OpenAPI()
            .info(new Info()
                    .title("E-commerce Toy API 문서")
                    .description("E-commerce Toy API document")
                    .version("v1")
            )
            .externalDocs(new ExternalDocumentation()
                    .description("프로젝트 README")
                    .url("")
            );
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("User API")
                .pathsToMatch("/v1/user/**")
                .addOpenApiCustomizer(openApi ->
                        openApi.info(new Info()
                                .title("User API")
                                .description("API documents related to User")
                                .version("1.0")
                        )
                )
            .build();
    }

    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
                .group("Product API")
                .pathsToMatch("/v1/product/**")
                .addOpenApiCustomizer(openApi ->
                    openApi.info(new Info()
                        .title("Product API")
                        .description("API documents related to Prodcut")
                        .version("1.0")
                    )
                )
            .build();
    }
}
