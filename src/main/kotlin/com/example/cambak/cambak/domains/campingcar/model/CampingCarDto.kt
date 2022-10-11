package com.example.cambak.cambak.domains.campingcar.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.Region
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

class CampingCarDto {
    //캠핑카 등록
    class EnrollCampingCarReq(
        @ApiModelProperty(name = "등록 상품명")
        var productName: String,
        @ApiModelProperty(name = "한줄 소개")
        var oneLineDescription: String,
        @ApiModelProperty(name = "캠핑카 소개")
        var description: String,
        @ApiModelProperty(name = "전화번호", value = "ex) 010-0000-0000")
        var mobileNo: String,
        @ApiModelProperty(name = "업체 주소")
        var address: String,
        @ApiModelProperty(name = "웹사이트 주소")
        var websiteAddress: String,
        @ApiModelProperty(name = "지역", value = "[KANGWON, KYUNGIN, JUNRA, KYUNGBUK, CHOONGCHUNG, BUSAN, SEOUL, JEJU]")
        var region: Region,
        @ApiModelProperty(name = "딜리버리 가능여부")
        var isDeliveryPossible: Boolean,
        @ApiModelProperty(name = "반려동물 동승 가능여부")
        var isPetPossible: Boolean,
        @ApiModelProperty(name = "동승가능인원")
        var passengersNumber: Long,
        @ApiModelProperty(name = "취침가능인원")
        var sleepPossibleNumber: Long,
        @ApiModelProperty(name = "자가용 주차 가능여부")
        var isParkingPossible: Boolean,
        @ApiModelProperty(name = "캠핑장비 기본제공 여부")
        var isEquipmentProvide: Boolean,
        @ApiModelProperty(name = "대여일 출고시간", value = "ex ) 09:00")
        var rentalTime: String,
        @ApiModelProperty(name = "반납일 반납시간", value = "ex ) 16:00")
        var returnTime: String,
        @ApiModelProperty(name = "운전자 최소 연령")
        var driverAgeLimit: Long,
        @ApiModelProperty(name = "운전자 면허종류", value = "[ONE_LARGE(\"1종 대형\"),ONE_NORMAL(\"1종 보통\"),TWO_NORMAL(\"2종 보통\"), SMALL_TOW(\"소형 견인차\")]")
        var driverLicense: LicenseCategory,
        @ApiModelProperty(name = "운전자 운전경력")
        var drivingExperience: Long,
        @ApiModelProperty(name = "평일 가격")
        var weekdayPrice: Long,
        @ApiModelProperty(name = "주말 가격")
        var weekendPrice: Long,
        @ApiModelProperty(name = "주중요금 (1박당)")
        var weekdayPriceByOnNight: Long,
        @ApiModelProperty(name = "주말 및 공휴일 요금 (1박당)")
        var weekendPriceByOnNight: Long,
        @ApiModelProperty(name = "2일 이상 예약시 대여금액 할인율(%)")
        var discountPercentByTwoDays: Long,
        @ApiModelProperty(name = "3일 이상 예약시 대여금액 할인율(%)")
        var discountPercentByThreeDays: Long,
        @ApiModelProperty(name = "최소 예약가능 일수", required = false)
        var possibleRentalDaysMin: Long,
        @ApiModelProperty(name = "최대 예약가능 일수", required = false)
        var possibleRentalDaysMax: Long,
        @ApiModelProperty(name = "기본 보유시설 체크한 것만 list", value = "ex) [air_conditioner,bed,...]", required = false)
        var basicConfigList: List<String> ?= null,
        @ApiModelProperty(name = "성수기/비성수기 요금 입력", required = false)
        var priceBySeasonList: List<PriceBySeason>? = null,
        @ApiModelProperty(name = "추가요금 (차량보험료 등 고객의사와 관계없이 기본적으로 추가되는 비용)", required = false)
        var priceBySurchargeList: List<PriceBySurcharge>? = null,
        @ApiModelProperty(name = "추가옵션 (텐트, 불멍세트, 바베큐세트, 스피커 등 고객이 선택하여 추가 구매를 할 수 있는 상품)", required = false)
        var additionalOptionList: List<AdditionalOptions>? = null
        ){
        enum class LicenseCategory(description: String){
            ONE_LARGE("1종 대형"),ONE_NORMAL("1종 보통"),TWO_NORMAL("2종 보통"), SMALL_TOW("소형 견인차")
        }
        class PriceBySeason(
            @ApiModelProperty(name = "시작일", value = "DD/MM")
            var startDay: String,
            @ApiModelProperty(name = "종료일", value = "DD/MM")
            var endDay: String,
            @ApiModelProperty(name = "주중요금 (1박당)")
            var weekDayPrice: Long,
            @ApiModelProperty(name = "주말요금 (1박당)")
            var weekendPrice: Long
        )
        class PriceBySurcharge(
            @ApiModelProperty(name = "서비스 명")
            var serviceName: String,
            @ApiModelProperty(name = "요금 추가방식", value = "[ONCE, PAY_BY_DAYS]")
            var payMethod: PayMethod,
            @ApiModelProperty(name = "요금")
            var price: Long
        ){
            enum class PayMethod{
                ONCE, PAY_BY_DAYS
            }
        }
        class AdditionalOptions(
            @ApiModelProperty(name = "추가옵션명")
            var optionName: String,
            @ApiModelProperty(name = "추가옵션 가격")
            var price: Long

        )
    }
    class EnrollCampingCarRes(
        code: Int,
//        var id:String
    ):Response(code)
    //캠핑카 삭제

    //캠핑카 수정

    //캠핑카 리스트업

    //캠핑카 상세조회

    //캠핑카 부가 시설 리스트업

    class ConfigListUpRes(
        code: Int,
        var configList: List<Config>
    ):Response(code){
        class Config(
            var key: String,
            var name: String,
        )
    }
}