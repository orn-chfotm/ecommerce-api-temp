package com.build.admin.core.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public GroupedOpenApi amdinApi() {
        return GroupedOpenApi.builder()
                .group("1. Admin API")
                .pathsToMatch("/v1/admin/**")
                .addOpenApiCustomizer(openApi ->
                        openApi.info(new Info()
                                .title("Admin API")
                                .description("API documents related to Admin")
                                .version("1.0")
                        )
                )
            .build();
    }

}
