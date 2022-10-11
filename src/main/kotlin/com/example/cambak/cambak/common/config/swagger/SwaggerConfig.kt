package com.example.cambak.cambak.common.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Response
import springfox.documentation.service.Server
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig(
    val swaggerProps: SwaggerProps
) {
    @Bean
    fun api(): Docket {
        val servers = swaggerProps.servers.map{
            Server(
                it.description,
                it.url,
                it.description,
                emptyList(),
                emptyList(),
            )
        }

        return Docket(DocumentationType.OAS_30)
            .servers(
                servers.first(),
                *servers.subList(
                    1,
                    servers.size
                ).toTypedArray()
            )
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .globalResponses(HttpMethod.GET, responses())
            .globalResponses(HttpMethod.DELETE, responses())
            .globalResponses(HttpMethod.POST, responses())
            .globalResponses(HttpMethod.PUT, responses())
    }

    private fun responses(): List<Response> {
        return mutableListOf(
            ResponseBuilder()
                .code("200")
                .description("OK")
                .build(),
            ResponseBuilder()
                .code("400")
                .description("Bad Request")
                .build(),
            ResponseBuilder()
                .code("401")
                .description("No Session")
                .build(),
            ResponseBuilder()
                .code("404")
                .description("Not Found")
                .build(),
            ResponseBuilder()
                .code("500")
                .description("Internal Server Error")
                .build()
        )
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("캠박 백엔드 서버 API")
            .description("캠박 백엔드 API 문서")
            .version("v1.0")
            .build()
    }
}