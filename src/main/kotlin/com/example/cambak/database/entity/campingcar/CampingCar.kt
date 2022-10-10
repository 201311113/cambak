package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob

@Entity
class CampingCar (

    @Column(length = 100)
    val productName: String,
    val oneLineDescription: String,
    @Lob
    val Description: String,
//    val images: List<String> ?= null,
    val mobileNo: String,
    val address: String,
    val websiteAddress: String,
    val region: Region,
    val isDeliveryPossible: Boolean,
    val isPetPossible: Boolean,
    val passengersNumber: Long,
    val sleepPossibleNumber: Long,
    val isParkingPossible: Boolean,
    val isEquipmentProvide: Boolean,

    val rentalTime: LocalDateTime,
    val returnTime: LocalDateTime,
    val driverAgeLimit: Long,
    val driverLicense: String,
    val drivingExperience: Long,
    val weekdayPrice: Long,
    val weekendPrice: Long,
    val weekdayPriceByOnNight: Long,
    val weekendPriceByOnNight: Long,
    val discountPercentByTwoDays: Long,
    val discountPercentByThreeDays: Long,
    val possibleRentalDaysMin: Long,
    val possibleRentalDaysMax: Long,

    //config
    //  기본 시설
    //  성수기 비수기 요금
    //  추가 요금
    //  추가 판매옵션

):BaseEntity()
enum class Region{
    KANGWON, KYUNGIN, JUNRA, KYUNGBUK, CHOONGCHUNG, BUSAN, SEOUL, JEJU
}