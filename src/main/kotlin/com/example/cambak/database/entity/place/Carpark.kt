package com.example.cambak.database.entity.place

import com.example.cambak.database.entity.ExternalReview
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue(value = "CARPARK")
class Carpark (
    name: String,
    address: String,
    oneLineDescription: String,
    description: String,
    lat: Double,
    lng: Double,
    phoneNo: String,
    region: Region,
    environment: Environment,

    @Column(length = 4)
    override var possibleCarType: String,    //in server에서 바이너리하게 관리 [일반차량, 트레일러, 카라반, 모터훔] ex) 1011 = [일반차량, 카라반, 모터훔]
): Place(
    name = name,
    address = address,
    oneLineDescription = oneLineDescription,
    region = region,
    description = description,
    phoneNo = phoneNo,
    lat = lat,
    lng = lng,
    reviewer = 0,
    totalScore = 0,
    environment = environment,
    possibleCarType = possibleCarType
){
    override fun getPlaceType(): PlaceType {
        return PlaceType.CARPARK
    }
}