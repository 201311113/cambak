package com.example.cambak.database.entity

import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.database.BaseEntity
import javax.persistence.*

@Entity
class CampingCar (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(length = 100)
    val productName: String,
    val oneLineDescription: String,
    @Lob
    val description: String,
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

    val rentalTime: String,
    val returnTime: String,
    val driverAgeLimit: Long,
    @Enumerated(value = EnumType.STRING)
    val driverLicense: CampingCarDto.EnrollCampingCarReq.LicenseCategory,
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