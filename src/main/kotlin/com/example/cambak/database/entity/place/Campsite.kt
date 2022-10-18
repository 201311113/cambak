package com.example.cambak.database.entity.place

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@DiscriminatorValue(value = "campsite")
@Table(name = "campsite")
class Campsite (
    name: String,
    address: String,
    addressDetail: String,
    lat: Double,
    lng: Double,

): Place(
    name = name,
    address = address,
    addressDetail = addressDetail,
    lat = lat,
    lng = lng
){
    override fun getPlaceType(): PlaceType {
        return PlaceType.CAMPSITE
    }
}