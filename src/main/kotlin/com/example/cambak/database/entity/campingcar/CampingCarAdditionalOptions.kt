package com.example.cambak.database.entity.campingcar

import com.example.cambak.database.BaseEntity
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Entity

@Entity
class CampingCarAdditionalOptions (
    val optionName: String,
    val price: Long

):BaseEntity()
