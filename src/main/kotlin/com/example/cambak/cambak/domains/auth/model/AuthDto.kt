package com.example.cambak.cambak.domains.auth.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.SignInType
import io.swagger.annotations.ApiModelProperty

class AuthDto {
    class SignInReq(
        @ApiModelProperty(name = "sns 로그인 시 snsId , email 로그인 시 email")
        var userId: String,
        @ApiModelProperty(value = "[KAKAO, APPLE, EMAIL]")
        var signInType: SignInType,
        var password: String ?= null,
        var snsToken: String ?= null,

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