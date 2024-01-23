package com.mercadolivro.config

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("mercado-livro")
            .pathsToMatch("api/customer/**", "/api/book/**")
            .build()
    }





/*
    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.mercadolivro.controller"))
        .paths(PathSelectors.any())
        .build()

*/


}