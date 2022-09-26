package com.example.cambak.cambak.common.util

class Response(
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

val msgMap = mapOf(
    OK to "성공",
    UNKNOWN_ERROR to "알 수 없는 오류가 발생했습니다.",
)