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
const val USER_NOT_FOUND = -1003
const val USER_IS_NOT_VENDOR = -1004

//캠핑카 Config 관련
const val CONFIG_TYPE_NOT_FOUND = -9000
const val ALREADY_CONFIG_KEY_EXIST = -9001
const val MOBILE_NO_INVALID = -9002
const val RENTAL_TIME_INVALID = -9003
const val BASIC_CONFIG_INVALID = -9004

val msgMap = mapOf(
    OK to "성공",
    UNKNOWN_ERROR to "알 수 없는 오류가 발생했습니다.",
    //로그인 관련
    EMAIL_NOT_FOUND to "이메일이 존재하지 않습니다.",
    EMAIL_ALREADY_EXISTED to "이미 존재하는 이메일 입니다.",
    PASSWORD_NOT_MATCHED to "비밀번호가 일치하지 않습니다.",
    USER_NOT_FOUND to "사용자를 찾을 수 없습니다.",
    USER_IS_NOT_VENDOR to "판매자 등록이되지 않은 유저입니다.",

    //캠핑카 Config 관련
    CONFIG_TYPE_NOT_FOUND to "부가시설 type을 찾을 수 없습니다.",
    ALREADY_CONFIG_KEY_EXIST to "이미 존재하는 부가시설 이름 입니다.",
    MOBILE_NO_INVALID to "전화번호 양식이 잘못되었습니다.",
    RENTAL_TIME_INVALID to "대여 시간 양식이 잘못되었습니다.",
    BASIC_CONFIG_INVALID to "유효하지 않은 기본 보유시설값 입니다."
)