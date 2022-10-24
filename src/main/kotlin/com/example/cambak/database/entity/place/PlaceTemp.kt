package com.example.cambak.database.entity.place

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "place_temp")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "place_type")
class PlaceTemp (
    @Id
    val id: Long,
    var name: String,
    var address: String,
    var oldAddress: String,
    var bio: String?,
    var isClosed: Long,
    var lat: Double?,
    var lng: Double?,
    var likes: Long,
    var price: String?,
    var contact: String?,
    var websiteUrl: String?,
    var score: Long,
    var view: Long,
    var themeId: String?,
    var regionId: String?,
    var imageId: String?,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var isDeleted: Long,
    var search: String,
    var filter: String,
    var isCampsite: Long,
)
//    var name: String,
//    var address: String,
//    var addressDetail: String,
////    @Column(nullable = false)
//    var lat: Double,
//    var lng: Double,
//): BaseEntity(){
//    fun getPlaceType(): PlaceType{
//        return PlaceType.PLACE
//    }
//}

//enum class PlaceType(val text: String){
//    CAMPSITE("캠핑장"), PLACE("초기상태")
//}