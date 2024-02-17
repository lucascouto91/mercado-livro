package com.mercadolivro.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("!prod")
@Configuration
class SwaggerConfig {

    @Bean
    fun api(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("mercado-livro")
            .pathsToMatch("/customers/**", "/books/**")
            .build()
    }

    @Bean
    fun springOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("API Mercado Livro")
                    .description("API para dados e status dos livros.")
                    .contact(Contact().name("Lucas Couto").url("https://github.com/lucascouto91"))
                    .version("v0.0.1")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("MercadoLivro Documentation")
                    .url("https://github.com/lucascouto91/mercado-livro")
            )

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