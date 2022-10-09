package com.example.cambak.cambak.common.util

import com.example.cambak.cambak.domains.auth.service.AuthService
import com.example.cambak.cambak.domains.campingcar.service.CampingCarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ServiceProvider {

    @Autowired
    lateinit var authService: AuthService
    @Autowired
    lateinit var campingcarService: CampingCarService
}