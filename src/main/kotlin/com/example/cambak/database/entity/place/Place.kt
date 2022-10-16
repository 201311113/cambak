package com.example.cambak.database.entity.place

import com.example.cambak.database.BaseEntity
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "place_type")
class Place (

    var name: String,
    var address: String,
    var addressDetail: String,
//    @Column(nullable = false)
    var lat: Double,
    var lng: Double,
): BaseEntity(){
    fun getPlaceType(): PlaceType{
        return PlaceType.PLACE
    }
}

enum class PlaceType(val text: String){
    CAMPSITE("캠핑장"), PLACE("초기상태")
}