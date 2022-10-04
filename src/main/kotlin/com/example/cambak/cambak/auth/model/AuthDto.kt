package com.example.cambak.cambak.auth.model

import com.example.cambak.cambak.common.util.Response

class AuthDto {
    class SignInReq(
        var email: String,
        var password: String,
    )

    class SignInRes(
        code:Int,
        var accessToken: String? = null
    ):Response(code)

    class SignUpReq(
        var email: String,
        var password: String,
        var nickname: String,
        var mobileNo: String,
        var isVendor: Boolean
    )

    class SignUpRes(
        code:Int
    ):Response(code)
}