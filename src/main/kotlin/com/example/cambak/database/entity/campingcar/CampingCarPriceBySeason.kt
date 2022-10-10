package com.example.cambak.database.entity.campingcar

import com.example.cambak.database.BaseEntity
import io.swagger.annotations.ApiModelProperty

//성수기, 비성수기 요금 (기획 Binary하지 않음)
class CampingCarPriceBySeason (
    val startDay: String,
    val endDay: String,
    val weekDayPrice: Long,
    val weekendPrice: Long
):BaseEntity()