package com.example.cambak.cambak.auth.controller

import com.example.cambak.cambak.auth.model.AuthDto
import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.ServiceProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    lateinit var serviceProvider: ServiceProvider

    @PostMapping("/register")
    fun signUp(
        @RequestBody req: AuthDto.SignUpReq,
    ): AuthDto.SignUpRes{
        try{
            return serviceProvider.authService.signUp(req)
        }catch (e: BadRequestException){
            return AuthDto.SignUpRes(e.code)
        }
    }


    @PostMapping("/token")
    fun signIn(
        @RequestBody req: AuthDto.SignInReq
    ): AuthDto.SignInRes{
        try{
            return serviceProvider.authService.signIn(req)
        }catch (e: BadRequestException){
            return AuthDto.SignInRes(e.code)
        }
    }
}