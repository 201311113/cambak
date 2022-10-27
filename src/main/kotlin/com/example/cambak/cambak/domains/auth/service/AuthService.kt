package com.example.cambak.cambak.domains.auth.service

import com.example.cambak.cambak.domains.auth.model.AuthDto

interface AuthService {
    fun signUpEmail(
        req: AuthDto.SignUpReq
    ): AuthDto.SignUpRes

    fun signIn(
        req: AuthDto.SignInReq
    ): AuthDto.SignInRes
}