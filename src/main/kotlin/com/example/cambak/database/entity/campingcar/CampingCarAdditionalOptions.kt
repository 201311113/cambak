package com.example.cambak.database.entity.campingcar

import com.example.cambak.database.BaseEntity
import com.example.cambak.database.entity.CampingCar
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class CampingCarAdditionalOptions (

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_car_id", nullable = false)
    val campingCar: CampingCar,
    val optionName: String,
    val price: Long

):BaseEntity()
