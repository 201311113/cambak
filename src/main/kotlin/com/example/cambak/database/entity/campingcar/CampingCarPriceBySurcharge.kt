package com.example.cambak.database.entity.campingcar

import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.database.BaseEntity
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

//추가 요금
@Entity
class CampingCarPriceBySurcharge (
    var serviceName: String,
    @Enumerated(value = EnumType.STRING)
    var payMethod: CampingCarDto.EnrollCampingCarReq.PriceBySurcharge.PayMethod,
    var price: Long
):BaseEntity()