package com.example.cambak.cambak.common.util

import com.example.cambak.cambak.auth.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ServiceProvider {

    @Autowired
    lateinit var authService: AuthService
}