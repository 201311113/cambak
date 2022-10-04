package com.example.cambak.cambak.auth.service

import com.example.cambak.cambak.auth.model.AuthDto

interface AuthService {
    fun signUp(
        req: AuthDto.SignUpReq
    ):AuthDto.SignUpRes

    fun signIn(
        req: AuthDto.SignInReq
    ):AuthDto.SignInRes
}