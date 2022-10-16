package com.example.cambak.database.entity

import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.cambak.domains.campingcar.model.LicenseCategory
import com.example.cambak.database.BaseEntity
import javax.persistence.*

@Entity
class CampingCar (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(length = 100)
    var productName: String,
    var oneLineDescription: String,
    @Lob
    var description: String,
//    var images: List<String> ?= null,
    var mobileNo: String,
    var address: String,
    var websiteAddress: String,
    var region: Region,
    var isDeliveryPossible: Boolean,
    var isPetPossible: Boolean,
    var passengersNumber: Long,
    var sleepPossibleNumber: Long,
    var isParkingPossible: Boolean,
    var isEquipmentProvide: Boolean,

    var rentalTime: String,
    var returnTime: String,
    var driverAgeLimit: Long,
    @Enumerated(value = EnumType.STRING)
    var driverLicense: LicenseCategory,
    var drivingExperience: Long,
    var weekdayPrice: Long,
    var weekendPrice: Long,
    var weekdayPriceByOnNight: Long,
    var weekendPriceByOnNight: Long,
    var discountPercentByTwoDays: Long,
    var discountPercentByThreeDays: Long,
    var possibleRentalDaysMin: Long,
    var possibleRentalDaysMax: Long,

    //config
    //  기본 시설
    //  성수기 비수기 요금
    //  추가 요금
    //  추가 판매옵션

):BaseEntity(){
    fun update(
        req: CampingCarDto.UpdateCampingCarReq
    ){

    }
}
enum class Region{
    KANGWON, KYUNGIN, JUNRA, KYUNGBUK, CHOONGCHUNG, BUSAN, SEOUL, JEJU
}