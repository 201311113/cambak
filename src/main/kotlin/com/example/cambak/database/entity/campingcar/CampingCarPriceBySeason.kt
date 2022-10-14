package com.example.cambak.database.entity.campingcar

import com.example.cambak.database.BaseEntity
import com.example.cambak.database.entity.CampingCar
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

//성수기, 비성수기 요금 (기획 Binary하지 않음)
@Entity
class CampingCarPriceBySeason (

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_car_id", nullable = false)
    val campingCar: CampingCar,
    var startDay: String,
    var endDay: String,
    var weekDayPrice: Long,
    var weekendPrice: Long
):BaseEntity()