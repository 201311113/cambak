package com.example.cambak.cambak.domains.auth.controller

import com.example.cambak.cambak.domains.auth.model.AuthDto
import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.common.util.ServiceProvider
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@Api(tags = ["[Auth] 로그인 관련 API"])
class AuthController {
    @Autowired
    lateinit var serviceProvider: ServiceProvider

    @ApiOperation(
        value = "회원가입 (이메일)",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PostMapping("/register")
    fun signUp(
        @RequestBody req: AuthDto.SignUpReq,
    ): AuthDto.SignUpRes{
        try{
            return serviceProvider.authService.signUpEmail(req)
        }catch (e: BadRequestException){
            return AuthDto.SignUpRes(e.code)
        }
    }

    @ApiOperation(
        value = "로그인 (sns 첫로그인 회원가입까지)",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
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
    @GetMapping("/test")
    fun test(

    ):Response{
        println("test success")
        return Response(OK)
    }
}