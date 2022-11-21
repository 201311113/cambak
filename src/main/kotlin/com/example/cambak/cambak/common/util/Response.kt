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
const val USER_IS_INCONSISTENT = -1005
const val PASSWORD_NOT_FOUND = -1006
const val SNS_TOKEN_NOT_FOUND = -1007

//캠핑카 관련
const val CAMPING_CAR_NOT_FOUND = -2000
const val USER_IS_INCONSISTENT_WITH_CAMPINGCAR_OWNER = -2001

//캠핑카 Review 관련
const val CAMPING_CAR_REVIEW_NOT_FOUND = -3000
const val USER_IS_INCONSISTENT_WITH_CAMPING_CAR_REVIEW_OWNER = -3001

//캠핑카 Reservation 관련
const val CAMPING_CAR_RESERVATION_NOT_FOUND = -4000
const val USER_IS_INCONSISTENT_WITH_CAMPING_CAR_RESERVATION_OWNER = -4001

//장소 관련
const val PLACE_NOT_FOUND = - 5000

//캠핑장 관련
const val CAMPSITE_NOT_FOUND = -6000
const val CAMPSITE_UPDATE_FAIL = -6001

//이미지 관련
const val IMAGE_UPLOAD_FAIL = -7000
const val IMAGE_DELETE_FAIL = -7001

//캠핑카 Config 관련
const val CONFIG_TYPE_NOT_FOUND = -9000
const val ALREADY_CONFIG_KEY_EXIST = -9001
const val MOBILE_NO_INVALID = -9002
const val RENTAL_TIME_INVALID = -9003
const val BASIC_CONFIG_INVALID = -9004

//장소 Config 관련
const val PLACE_CONFIG_NOT_FOUND = -9400


val msgMap = mapOf(
    OK to "성공",
    UNKNOWN_ERROR to "알 수 없는 오류가 발생했습니다.",
    //로그인 관련
    EMAIL_NOT_FOUND to "이메일이 존재하지 않습니다.",
    EMAIL_ALREADY_EXISTED to "이미 존재하는 이메일 입니다.",
    PASSWORD_NOT_MATCHED to "비밀번호가 일치하지 않습니다.",
    USER_NOT_FOUND to "사용자를 찾을 수 없습니다.",
    USER_IS_NOT_VENDOR to "판매자 등록이되지 않은 유저입니다.",
    USER_IS_INCONSISTENT to "사용자가 불일치 합니다.",
    PASSWORD_NOT_FOUND to "이메일 로그인 비밀번호가 null값입니다.",
    SNS_TOKEN_NOT_FOUND to "SNS 로그인 토큰이 null값입니다.",

    //캠핑카 관련
    CAMPING_CAR_NOT_FOUND to "캠핑카를 찾을 수 없습니다.",
    USER_IS_INCONSISTENT_WITH_CAMPINGCAR_OWNER to "캠핑카 소유자와 현재 접근하는 사용자가 불일치 합니다.",

    //캠핑카 Review 관련
    CAMPING_CAR_REVIEW_NOT_FOUND to "캠핑카 리뷰를 찾을 수 없습니다.",
    USER_IS_INCONSISTENT_WITH_CAMPING_CAR_REVIEW_OWNER to "캠핑카 리뷰 작성자와 현재 접근하는 사용자가 불일치 합니다.",

    //캠핑카 Reservation 관련
    CAMPING_CAR_RESERVATION_NOT_FOUND to "캠핑카 예약을 찾을 수 없습니다.",
    USER_IS_INCONSISTENT_WITH_CAMPING_CAR_RESERVATION_OWNER to "캠핑카 예약자와 현재 접근하는 사용자가 불일치 합니다.",

    //장소 관련
    PLACE_NOT_FOUND to "장소를 찾을 수 없습니다.",

    //캠핑장 관련
    CAMPSITE_NOT_FOUND to "캠핑장을 찾을 수 없습니다.",
    CAMPSITE_UPDATE_FAIL to "캠핑장 정보 업데이트에 실패하였습니다.",

    //이미지 관련
    IMAGE_UPLOAD_FAIL to "이미지 업로드에 실패하였습니다.",
    IMAGE_DELETE_FAIL to "이미지 삭제에 실패하였습니다.",

    //캠핑카 Config 관련
    CONFIG_TYPE_NOT_FOUND to "부가시설 type을 찾을 수 없습니다.",
    ALREADY_CONFIG_KEY_EXIST to "이미 존재하는 부가시설 이름 입니다.",
    MOBILE_NO_INVALID to "전화번호 양식이 잘못되었습니다.",
    RENTAL_TIME_INVALID to "대여 시간 양식이 잘못되었습니다.",
    BASIC_CONFIG_INVALID to "유효하지 않은 기본 보유시설값 입니다.",

    //장소 Config 관련
    PLACE_CONFIG_NOT_FOUND to "해당 장소 기본, 부대시설, 해시태그등을 찾을 수 없습니다"
)