package com.example.cambak.database.entity.campingcar

import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.cambak.domains.campingcar.model.PayMethod
import com.example.cambak.database.BaseEntity
import com.example.cambak.database.entity.CampingCar
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

//추가 요금
@Entity
class CampingCarPriceBySurcharge (

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_car_id", nullable = false)
    val campingCar: CampingCar,
    var serviceName: String,
    @Enumerated(value = EnumType.STRING)
    var payMethod: PayMethod,
    var price: Long
):BaseEntity()