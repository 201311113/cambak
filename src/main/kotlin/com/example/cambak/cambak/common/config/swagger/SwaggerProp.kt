package com.example.cambak.cambak.common.config.swagger

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "swagger", ignoreUnknownFields = false)
class SwaggerProps {
    lateinit var servers: List<ServerObject>

    class ServerObject{
        lateinit var description: String
        lateinit var url: String
    }
}