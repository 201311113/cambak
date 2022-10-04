package com.example.cambak.cambak.common.util

open class Response(
    val code: Int,
    val msg: String? = null
) {
    constructor(code: Int): this(
        code,
        msgMap[code]
    )

}

const val OK = 0
const val UNKNOWN_ERROR = -1

//로그인 관련
const val EMAIL_NOT_FOUND = -1000
const val EMAIL_ALREADY_EXISTED = -1001
const val PASSWORD_NOT_MATCHED = -1002

val msgMap = mapOf(
    OK to "성공",
    UNKNOWN_ERROR to "알 수 없는 오류가 발생했습니다.",
    //로그인 관련
    EMAIL_NOT_FOUND to "이메일이 존재하지 않습니다.",
    EMAIL_ALREADY_EXISTED to "이미 존재하는 이메일 입니다.",
    PASSWORD_NOT_MATCHED to "비밀번호가 일치하지 않습니다."
)