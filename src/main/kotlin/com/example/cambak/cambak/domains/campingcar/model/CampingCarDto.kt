package com.example.cambak.cambak.domains.campingcar.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.Region
import java.time.LocalDateTime

class CampingCarDto {
    //캠핑카 등록
    class EnrollCampingCarReq(
        var productName: String,
        var oneLineDescription: String,
        var Description: String,
        var mobileNo: String,
        var address: String,
        var websiteAddress: String,
        var region: Region,
        var rentalTime: LocalDateTime,
        var returnTime: LocalDateTime,
        var driverAgeLimit: Long,
        var driverLicense: String,
        var drivingExperience: Long,
        var weekdayPrice: Long,
        var weekendPrice: Long,
        var weekdayPriceByOnNight: Long,
        var weekendPriceByOnNight: Long,
        var discountPercentByTwoDays: Long,
        var discountPercentByThreeDays: Long,
        var possibleRentalDaysMin: Long,
        var possibleRentalDaysMax: Long,
        )
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