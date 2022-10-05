package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import javax.persistence.Column
import javax.persistence.Lob

class CampingCar (

    @Column(length = 100)
    val productName: String,
    val oneLineDescription: String,
    @Lob
    val Description: String,
    val images: List<String> ?= null,
    val mobileNo: String,
    val address: String,
    val websiteAddress: String,

):BaseEntity()